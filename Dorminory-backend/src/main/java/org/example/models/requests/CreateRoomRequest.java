package org.example.models.requests;

public record CreateRoomRequest(String roomNumber, Integer capacity, Long dormitoryId) {
}
