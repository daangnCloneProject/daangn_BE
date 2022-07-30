//package com.example.daangn.exception;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.support.ChannelInterceptor;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class StompHandler implements ChannelInterceptor {
//    private final JwtTokenProvider jwtTokenProvider;
//
//    @Override
//    public Message<?> preSend(Message<?> message, MessageChannel channel) {
//        // StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
//        // System.out.println("message:" + message);
//        // System.out.println("헤더 : " + message.getHeaders());
//        // System.out.println("토큰" + accessor.getNativeHeader("Authorization"));
//        // if (StompCommand.CONNECT.equals(accessor.getCommand())) {
//        //     jwtTokenProvider.validateToken(Objects.requireNonNull(accessor.getFirstNativeHeader("Authorization")).substring(7));
//        // }
//        return message;
//    }
//}
