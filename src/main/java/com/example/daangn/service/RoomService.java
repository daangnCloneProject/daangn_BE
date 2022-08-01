//package com.example.daangn.service;
//
//
//import com.example.daangn.dto.ResponseDto;
//import com.example.daangn.dto.RoomRequestDto;
//import com.example.daangn.model.Post;
//import com.example.daangn.model.Room;
//import com.example.daangn.model.User;
//import com.example.daangn.repository.post.PostRepository;
//import com.example.daangn.repository.UserRepository;
//import com.example.daangn.security.UserDetailsImpl;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//@RequiredArgsConstructor
//@Service
//public class RoomService {
//    private final PostRepository postRepository;
//    private final UserRepository userRepository;
//
//    public ResponseEntity<?> getRooms(UserDetailsImpl userDetails) {
//
//    }
//
//    public ResponseEntity<?> getRoom() {
//
//    }
//
//    public ResponseEntity<ResponseDto<?>> createRoom(RoomRequestDto roomRequestDto, UserDetailsImpl userDetails) {
//        Post post = postRepository.findById(roomRequestDto.getPostId())
//                .orElseThrow( () -> new IllegalArgumentException("게시글이 존재하지 않습니다"));
//        User seller = post.getUser();
//        Room room = new Room(post, seller, userDetails.getUser());
//    }
//
//    public ResponseEntity<?> deleteRoom(Long roomId, UserDetailsImpl userDetails) {
//
//    }
//}
