package com.example.daangn.service;


import com.example.daangn.dto.MessageResponseDto;
import com.example.daangn.dto.ResponseDto;
import com.example.daangn.dto.RoomRequestDto;
import com.example.daangn.dto.RoomResultDto;
import com.example.daangn.model.Post;
import com.example.daangn.model.Room;
import com.example.daangn.model.User;
import com.example.daangn.repository.MessageRepository;
import com.example.daangn.repository.RoomRepository;
import com.example.daangn.repository.post.PostRepository;
import com.example.daangn.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RoomService {
    private final PostRepository postRepository;
    private final MessageRepository messageRepository;
    private final RoomRepository roomRepository;

    public ResponseEntity<List<RoomResultDto>> getRooms(UserDetailsImpl userDetails) {
        List<Room> roomList = roomRepository.findAllByBuyerOrSeller(userDetails.getUser(), userDetails.getUser());
        List<RoomResultDto> roomResultDtoList = new ArrayList<>();
        for (Room room : roomList){
            RoomResultDto roomResultDto = new RoomResultDto(room);
            roomResultDtoList.add(roomResultDto);
        }
        return new ResponseEntity<>(roomResultDtoList, HttpStatus.OK);
    }

    public ResponseEntity<RoomResultDto> getRoomDetails(Long postId, UserDetailsImpl userDetails) {
        Room room = roomRepository.findByPostIdAndBuyer(postId, userDetails.getUser());
        if(room == null) {
            room = roomRepository.findByPostIdAndSeller(postId, userDetails.getUser());
        }
        List<MessageResponseDto> messageResponseDtoList = messageRepository.findAllByRoomId(room.getId());
        if(room!=null)
            return new ResponseEntity<>(new RoomResultDto(room, messageResponseDtoList), HttpStatus.OK);
        else throw new IllegalArgumentException("????????? ?????? ???????????? ????????? ???????????????");
    }


    @Transactional
    public ResponseEntity<ResponseDto> createRoom(RoomRequestDto roomRequestDto, UserDetailsImpl userDetails) {
        Post post = postRepository.findById(roomRequestDto.getPostId())
                .orElseThrow( () -> new IllegalArgumentException("???????????? ???????????? ????????????"));
        User seller = post.getUser();
        Room findroom = roomRepository.findByPostIdAndBuyer(roomRequestDto.getPostId(), userDetails.getUser());
        // ???????????? ??????????????? ????????? ?????? ??????
        if(seller.getId().equals(userDetails.getUser().getId())) {
            throw new IllegalArgumentException("???????????? ???????????? ?????? ??? ????????????.");
        } else if (findroom != null)
            return new ResponseEntity<>(new ResponseDto(false, "?????? ?????? ???????????????", findroom.getId()), HttpStatus.BAD_REQUEST);
        Room room = new Room(post, seller, userDetails.getUser());
        roomRepository.save(room);
        
        return new ResponseEntity<>(new ResponseDto(true, "??? ?????? ??????", room.getId()), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<ResponseDto> deleteRoom(Long roomId, UserDetailsImpl userDetails) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow( () -> new IllegalArgumentException("?????? ???????????? ????????????"));
        if((!room.getSeller().getId().equals(userDetails.getUser().getId())) && (!room.getBuyer().getId().equals(userDetails.getUser().getId()))) {
            throw new IllegalArgumentException("?????? ????????? ????????????.");
        }
        roomRepository.deleteById(roomId);
        return new ResponseEntity<>(new ResponseDto(true, "?????? ??????"), HttpStatus.OK);
    }
}