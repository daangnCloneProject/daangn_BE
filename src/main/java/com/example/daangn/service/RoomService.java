package com.example.daangn.service;


import com.example.daangn.dto.ResponseDto;
import com.example.daangn.dto.RoomRequestDto;
import com.example.daangn.dto.RoomResultDto;
import com.example.daangn.model.Post;
import com.example.daangn.model.Room;
import com.example.daangn.model.User;
import com.example.daangn.repository.RoomRepository;
import com.example.daangn.repository.post.PostRepository;
import com.example.daangn.repository.UserRepository;
import com.example.daangn.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RoomService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
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

    public ResponseEntity<?> getRoom(Long roomId, UserDetailsImpl userDetails) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow( () -> new IllegalArgumentException("방이 존재하지 않습니다."));
//        if(room.getBuyer() == userDetails.getUser()) {
//            RoomResultDto roomResultDto = new RoomResultDto(room, room.getSeller(), userDetails.getUser());
//            return new ResponseEntity<>(roomResultDto, HttpStatus.OK);
//        } else if (room.getSeller() == userDetails.getUser()) {
//            RoomResultDto roomResultDto = new RoomResultDto(room.getBuyer(), roomId, userDetails.getUser());
//            return new ResponseEntity<>(roomResultDto, HttpStatus.OK);
//        }
        if(room.getBuyer().equals(userDetails.getUser()) || room.getSeller().equals(userDetails.getUser())){
            return new ResponseEntity<>(new RoomResultDto(room), HttpStatus.OK);
        }
        else throw new IllegalArgumentException("구매자 혹은 판매자만 조회가 가능합니다");
    }

    public ResponseEntity<ResponseDto<?>> createRoom(RoomRequestDto roomRequestDto, UserDetailsImpl userDetails) {
        Post post = postRepository.findById(roomRequestDto.getPostId())
                .orElseThrow( () -> new IllegalArgumentException("게시글이 존재하지 않습니다"));
        User seller = post.getUser();
        // 구매자만 판매글에서 채팅방 개설 가능
        if(seller.getId().equals(userDetails.getUser().getId())) {
            throw new IllegalArgumentException("판매자는 채팅방을 만들 수 없습니다.");
        }
        Room room = new Room(post, seller, userDetails.getUser());
        roomRepository.save(room);
        return new ResponseEntity<>(new ResponseDto<>(true, "방 생성 성공"), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDto<?>> deleteRoom(Long roomId, UserDetailsImpl userDetails) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow( () -> new IllegalArgumentException("방이 존재하지 않습니다"));
        if((!room.getSeller().getId().equals(userDetails.getUser().getId())) && (!room.getBuyer().getId().equals(userDetails.getUser().getId()))) {
            throw new IllegalArgumentException("접근 권한이 없습니다.");
        }
        roomRepository.deleteById(roomId);
        return new ResponseEntity<>(new ResponseDto<>(true, "삭제 성공"), HttpStatus.OK);
    }
}