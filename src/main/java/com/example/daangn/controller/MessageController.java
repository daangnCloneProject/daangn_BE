//package com.example.daangn.controller;
//
//
//import com.example.daangn.dto.MessageRequestDto;
//import com.example.daangn.security.UserDetailsImpl;
//import com.example.daangn.service.MessageService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.messaging.handler.annotation.DestinationVariable;
//import org.springframework.messaging.handler.annotation.Header;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//@RequiredArgsConstructor
//@RestController
//public class MessageController {
//
//    private final SimpMessagingTemplate template;
//    private final MessageService messageService;
//
//    @GetMapping("/api/message/{roomId}")
//    public ResponseEntity<?> getMessages(@PathVariable Long roomId,
//                                         @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        return ResponseEntity.ok().body(messageService.getMessages(roomId, userDetails));
//    }
//
//    @MessageMapping(value = {"/message","/message/{roomId}"})
//    public ResponseEntity<?> createMessage(@AuthenticationPrincipal UserDetailsImpl userDetails,
//                                           MessageRequestDto messageRequestDto,
//                                           @Header("Authorization") String token, @DestinationVariable Long roomId) {
////        MessageResponseDto messageResponseDto = messageService.createMessage(messageRequestDto, token, roomId); // TODO response 맞추기
////        template.convertAndSend("/sub/channel/" + roomId, messageResponseDto); // TODO 뭔지 알아보기
//        return new ResponseEntity<>("ㅎㅇ", HttpStatus.OK);
//    }
//}
