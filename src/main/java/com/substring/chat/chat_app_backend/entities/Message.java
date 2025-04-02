package com.substring.chat.chat_app_backend.entities;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Message {

   private String sender;

   private String content;

   private LocalDateTime time;

   public Message(String sender, String content) {
      this.sender = sender;
      this.content = content;
      this.time = LocalDateTime.now();
   }

}
