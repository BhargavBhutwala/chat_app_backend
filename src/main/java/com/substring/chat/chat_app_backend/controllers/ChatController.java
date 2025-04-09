package com.substring.chat.chat_app_backend.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import com.substring.chat.chat_app_backend.entities.Message;
import com.substring.chat.chat_app_backend.entities.Room;
import com.substring.chat.chat_app_backend.repositories.RoomRepository;
import com.substring.chat.payload.MessageRequest;

@Controller
@CrossOrigin("http://localhost:5173")
public class ChatController {

   @Autowired
   private RoomRepository roomRepository;

   // when a client sends a message to the WebSocket destination
   // /sendMessage/{roomId}, Spring will route that message to this method. it acts
   // like a controller endpoint specifically for incoming WebSocket messages
   @MessageMapping("/sendMessage/{roomId}")
   // This tells Spring that after processing the incoming message, the return
   // value (Message) should be sent to all clients that are subscribed to the
   // /topic/room/{roomId} destination. It broadcasts the message to everyone in
   // the specific chat room
   @SendTo("/topic/room/{roomId}")
   public Message sendMessage(@DestinationVariable String roomId, @RequestBody MessageRequest request) {

      Room room = roomRepository.findByRoomId(request.getRoomId());

      Message message = new Message();
      message.setSender(request.getSender());
      message.setContent(request.getContent());
      message.setTime(LocalDateTime.now());

      if (room != null) {
         room.getMessages().add(message);
         roomRepository.save(room);
         return message;
      } else {
         throw new RuntimeException("Room does not exist!");
      }

   }
}
