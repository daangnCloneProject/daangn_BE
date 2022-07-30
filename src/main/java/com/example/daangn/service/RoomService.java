package com.example.daangn.service;


import com.example.daangn.dto.ResponseDto;
import com.example.daangn.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoomService {
    public ResponseEntity<?> getRooms(UserDetailsImpl userDetails) {
    }

    public ResponseEntity<?> getRoom() {
    }

    public ResponseEntity<ResponseDto<?>> createRoom(RoomRequestDto roomRequestDto, UserDetailsImpl userDetails) {
    }

    public ResponseEntity<?> deleteRoom(Long roomId, UserDetailsImpl userDetails) {

    }
}
