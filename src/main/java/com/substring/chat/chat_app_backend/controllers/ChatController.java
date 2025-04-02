package com.substring.chat.chat_app_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.substring.chat.chat_app_backend.repositories.RoomRepository;

@Controller
public class ChatController {

   @Autowired
   private RoomRepository roomRepository;

   // method for sending and receiving messages
}
