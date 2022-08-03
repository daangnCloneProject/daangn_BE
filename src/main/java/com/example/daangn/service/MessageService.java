package com.example.daangn.service;

import com.example.daangn.dto.MessageRequestDto;
import com.example.daangn.dto.MessageResponseDto;
import com.example.daangn.model.Message;
import com.example.daangn.model.Room;
import com.example.daangn.repository.MessageRepository;
import com.example.daangn.repository.RoomRepository;
import com.example.daangn.security.UserDetailsImpl;
import com.example.daangn.security.provider.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MessageService {
    private JwtTokenProvider jwtTokenProvider;
    private final MessageRepository messageRepository;
    private final RoomRepository roomRepository;

    public List<MessageResponseDto> getMessages(Long roomId, UserDetailsImpl userDetails) {
//        validateRole(roomId, userDetails);
        List<Message> messages = messageRepository.findAllByRoomId(roomId);
        List<MessageResponseDto> messageResponseDtoList = new ArrayList<>();
        for(Message message : messages){
            MessageResponseDto messageResponseDto = new MessageResponseDto(message);
            messageResponseDtoList.add(messageResponseDto);
        }
        return messageResponseDtoList;
    }

    @Transactional
    public MessageResponseDto createMessage(MessageRequestDto messageRequestDto/*, String token*/, Long roomId) {
//        Authentication authentication = jwtTokenProvider.getAuthentication(token);
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        Room room = validateRole(roomId, );
        Room room = roomRepository.findById(roomId)
                .orElseThrow( () -> new IllegalArgumentException("없어요"));
        Message message = new Message(messageRequestDto, room);
        System.out.println(message.toString());
        messageRepository.save(message);
        return new MessageResponseDto(message);
    }

//    private Room validateRole(Long roomId, UserDetailsImpl userDetails) throws IllegalArgumentException {
//        Room room = roomRepository.findById(roomId).orElseThrow(() ->
//                new IllegalArgumentException("채널이 존재하지 않습니다.")
//        );
//        if (userDetails == null) {
//            throw new IllegalArgumentException("로그인이 필요합니다");
//        }
//        return room;
//    }

}
