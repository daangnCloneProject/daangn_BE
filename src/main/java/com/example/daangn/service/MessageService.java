//package com.example.daangn.service;
//
//import com.example.daangn.dto.MessageRequestDto;
//import com.example.daangn.dto.ResponseDto;
//import com.example.daangn.security.UserDetailsImpl;
//import org.springframework.http.ResponseEntity;
//
//import java.util.stream.Collectors;
//
//public class MessageService {
//
//    public ResponseEntity<?> getMessages(Long roomId, UserDetailsImpl userDetails) {
//        validateRole(roomId, userDetails);
//        return new ResponseDto<>(true, messageRepository.findTop100ByChannelIdOrderByCreatedAtDesc(roomId)
//                .stream().map(MessageDTO::new).collect(Collectors.toList())); //TODO 참고해서 PostService 반복문 수정
//    }
//
//    public MessageRequestDto createMessage(MessageRequestDto messageRequestDto, String token, Long roomId) {
//        Authentication authentication = jwtTokenProvider.getAuthentication(token);
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        Channel channel = validateRole(channelId, userDetails);
//        messageRepository.save(Message.builder()
//                .message(messageDto.getMessage())
//                .user(userDetails.getUser())
//                .channel(channel)
//                .build());
//        messageDto.setUserId(userDetails.getUser().getId());
//        messageDto.setUsername(userDetails.getUsername());
//        messageDto.setNickname(userDetails.getUser().getNickname());
//        messageDto.setIconUrl(userDetails.getUser().getIconUrl());
//        return messageDto; // template이 메세지 보내는 기능?
//    }
//}
