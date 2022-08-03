package com.example.daangn.service;


import com.example.daangn.dto.MessageResponseDto;
import com.example.daangn.dto.ResponseDto;
import com.example.daangn.dto.RoomRequestDto;
import com.example.daangn.dto.RoomResultDto;
import com.example.daangn.model.Message;
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

    public ResponseEntity<?> getRooms(UserDetailsImpl userDetails) {
        List<Room> roomList = roomRepository.findAllByBuyerOrSeller(userDetails.getUser(), userDetails.getUser());
        List<RoomResultDto> roomResultDtoList = new ArrayList<>();
        for (Room room : roomList){
            RoomResultDto roomResultDto = new RoomResultDto(room);
            roomResultDtoList.add(roomResultDto);
        }
        return new ResponseEntity<>(roomResultDtoList, HttpStatus.OK);
    }

    public ResponseEntity<?> getRoomDetails(Long postId, UserDetailsImpl userDetails) {
        Room room = roomRepository.findByPostIdAndBuyer(postId, userDetails.getUser());
        List<Message> messageList = messageRepository.findAllByRoomId(room.getId());
        List<MessageResponseDto> messageResponseDtoList = new ArrayList<>();
        for (Message message : messageList){
            MessageResponseDto messageResponseDto = new MessageResponseDto(message);
            messageResponseDtoList.add(messageResponseDto);
        }

        if(room!=null)
            return new ResponseEntity<>(new RoomResultDto(room, messageResponseDtoList), HttpStatus.OK);
        else throw new IllegalArgumentException("구매자 혹은 판매자만 조회가 가능합니다");
    }


    @Transactional
    public ResponseEntity<ResponseDto> createRoom(RoomRequestDto roomRequestDto, UserDetailsImpl userDetails) {
        Post post = postRepository.findById(roomRequestDto.getPostId())
                .orElseThrow( () -> new IllegalArgumentException("게시글이 존재하지 않습니다"));
        User seller = post.getUser();
        Room findroom = roomRepository.findByPostIdAndBuyer(roomRequestDto.getPostId(), userDetails.getUser());
        // 구매자만 판매글에서 채팅방 개설 가능
        if(seller.getId().equals(userDetails.getUser().getId())) {
            throw new IllegalArgumentException("판매자는 채팅방을 만들 수 없습니다.");
        } else if (findroom != null)
            return new ResponseEntity<>(new ResponseDto<>(false, "방이 이미 존재합니다", findroom.getId()), HttpStatus.BAD_REQUEST);
        Room room = new Room(post, seller, userDetails.getUser());
        roomRepository.save(room);
        
        return new ResponseEntity<>(new ResponseDto(true, "방 생성 성공"), HttpStatus.OK);
    }

    @Transactoinal
    public ResponseEntity<ResponseDto> deleteRoom(Long roomId, UserDetailsImpl userDetails) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow( () -> new IllegalArgumentException("방이 존재하지 않습니다"));
        if((!room.getSeller().getId().equals(userDetails.getUser().getId())) && (!room.getBuyer().getId().equals(userDetails.getUser().getId()))) {
            throw new IllegalArgumentException("접근 권한이 없습니다.");
        }
        roomRepository.deleteById(roomId);
        return new ResponseEntity<>(new ResponseDto(true, "삭제 성공"), HttpStatus.OK);
    }
}