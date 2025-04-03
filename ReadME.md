# Chat App Backend

A simple chat backend application built using Spring Boot, MongoDB, and WebSocket STOMP integration. This project enables real-time messaging, room management, and message persistence for a chat application.

## Features

- **Real-Time Communication:** Uses WebSocket with STOMP protocol to enable instant messaging.
- **Room Management:** Create, join, and manage chat rooms.
- **Message History:** Stores messages in MongoDB with pagination support.
- **Fallback Support:** Integrates SockJS to provide fallback options for browsers that do not support WebSocket.

## Technologies Used

- **Spring Boot:** Framework for building the backend application.
- **Spring WebSocket:** For enabling real-time communication between clients and the server.
- **Spring Data MongoDB:** To integrate MongoDB as the NoSQL database for storing rooms and messages.
- **STOMP:** Messaging protocol for handling WebSocket communications.
- **SockJS:** Provides fallback options when native WebSocket connections cannot be established.
- **Lombok:** Reduces boilerplate code with annotations for getters, setters, constructors, etc.
- **MongoDB:** NoSQL database for persisting chat data.

## Getting Started

### Prerequisites

- **Java 8 or higher**
- **Maven or Gradle**
- **MongoDB:** Make sure you have a MongoDB instance running locally or remotely.
