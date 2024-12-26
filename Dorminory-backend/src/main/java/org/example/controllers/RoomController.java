package org.example.controllers;

import org.example.models.Room;
import org.example.models.dto.RoomDto;
import org.example.models.requests.CreateRoomRequest;
import org.example.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping
    public List<RoomDto> getAllRooms() {
        return roomService.getAllRooms().stream()
                .map(room -> new RoomDto(room.getRoomId(), room.getRoomNumber(), room.getCapacity(), room.getOccupiedBeds()))
                .toList();
    }

    @GetMapping("/{id}")
    public Optional<RoomDto> getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id)
                .map(room -> new RoomDto(room.getRoomId(), room.getRoomNumber(), room.getCapacity(), room.getOccupiedBeds()));
    }

    @PostMapping
    public Room createRoom(@RequestBody CreateRoomRequest request) {
        Room room = new Room();
        room.setRoomNumber(request.roomNumber());
        room.setCapacity(request.capacity());
        return roomService.createRoom(room);
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
    }
}
