package com.example.daangn.socket;

import com.example.daangn.repository.RoomRepository;
import com.example.daangn.repository.UserRepository;
import com.example.daangn.security.jwt.JwtDecoder;
import com.example.daangn.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StompHandler implements ChannelInterceptor {
    private final JwtDecoder jwtDecoder;
    private final MessageService messageService;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
//        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
//        String accessToken = accessor.getFirstNativeHeader("Authorization");
//        if (StompCommand.CONNECT == accessor.getCommand()) { // Websocket 연결 요청
//            String username = jwtDecoder.decodeUsername(accessToken);
//            System.out.println(username);
////            userConnectionService.manageConnectedUser(userId);
//
//        } else if(StompCommand.DISCONNECT == accessor.getCommand()) {
//            String username = jwtDecoder.decodeUsername(accessToken);
//
//            if(username != null) {
//
//            }
//        }
        return message;
    }
}
