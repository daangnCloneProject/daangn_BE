package com.example.daangn.repository;

import com.example.daangn.model.Room;
import com.example.daangn.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findAllByBuyerOrSeller(User buyer, User seller);
    Room findByPostIdAndBuyer(Long postId, User buyer);
}
