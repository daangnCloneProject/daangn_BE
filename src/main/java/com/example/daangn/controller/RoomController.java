package com.example.daangn.controller;


import com.example.daangn.dto.ResponseDto;
import com.example.daangn.dto.RoomRequestDto;
import com.example.daangn.model.User;
import com.example.daangn.security.UserDetailsImpl;
import com.example.daangn.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/api/myrooms")
    public ResponseEntity<?> getRooms(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        return roomService.getRooms(userDetails);
    }

    @GetMapping("/api/room/{postId}")
    public ResponseEntity<?> getRoomDetails(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return roomService.getRoomDetails(postId, userDetails);
    }

    @PostMapping("/api/room")
    public ResponseEntity<ResponseDto> createRoom(@RequestBody RoomRequestDto roomRequestDto,
                                                     @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        return roomService.createRoom(roomRequestDto, userDetails);
    }

    @DeleteMapping("/api/room/{roomId}")
    public ResponseEntity<ResponseDto> deleteRoom(@PathVariable Long roomId,
                                                     @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return roomService.deleteRoom(roomId, userDetails);
    }
}
