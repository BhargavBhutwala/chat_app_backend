package com.substring.chat.chat_app_backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.substring.chat.chat_app_backend.entities.Message;
import com.substring.chat.chat_app_backend.entities.Room;
import com.substring.chat.chat_app_backend.repositories.RoomRepository;

@RestController
@RequestMapping("/api/v1/room")
@CrossOrigin("http://localhost:5173")
public class RoomController {

   @Autowired
   private RoomRepository roomRepository;

   // create room
   @PostMapping("/")
   public ResponseEntity<?> createRoom(@RequestBody String roomId) {

      if (roomRepository.findByRoomId(roomId) != null) {
         // room already exists
         return ResponseEntity.badRequest().body("Room already exists!");
      }

      // create new room
      Room room = new Room();
      room.setRoomId(roomId);

      roomRepository.save(room);

      return ResponseEntity.status(HttpStatus.CREATED).body(room);
   }

   // get room: join room
   @GetMapping("/{roomId}")
   public ResponseEntity<?> joinRoom(@PathVariable(value = "roomId") String roomId) {

      Room room = roomRepository.findByRoomId(roomId);

      if (room == null) {
         return ResponseEntity.badRequest().body("Room does not exist!");
      }

      return ResponseEntity.ok(room);
   }

   // get all messages in room
   @GetMapping("/{roomId}/messages")
   public ResponseEntity<List<Message>> getMessages(@PathVariable(value = "roomId") String roomId,
         @RequestParam(value = "page", defaultValue = "0", required = false) int page,
         @RequestParam(value = "size", defaultValue = "10", required = false) int size) {

      Room room = roomRepository.findByRoomId(roomId);

      if (room == null) {
         return ResponseEntity.badRequest().build();
      }

      List<Message> messages = room.getMessages();

      int end = messages.size() - page * size;
      int start = Math.max(0, end - size);

      List<Message> pageMessages = messages.subList(start, end);

      return ResponseEntity.ok(pageMessages);
   }
}
