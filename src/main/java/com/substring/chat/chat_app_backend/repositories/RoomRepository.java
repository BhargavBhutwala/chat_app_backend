package com.substring.chat.chat_app_backend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.substring.chat.chat_app_backend.entities.Room;

public interface RoomRepository extends MongoRepository<Room, String> {

   public Room findByRoomId(String roomId);

}
