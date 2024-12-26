package org.example.controllers;

import org.example.models.Dormitory;
import org.example.models.dto.DormitoryDto;
import org.example.models.requests.CreateDormitoryRequest;
import org.example.services.DormitoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dormitories")
public class DormitoryController {
    @Autowired
    private DormitoryService dormitoryService;

    @GetMapping
    public List<DormitoryDto> getAllDormitories() {
        return dormitoryService.getAllDormitories().stream()
                .map(dormitory -> new DormitoryDto(dormitory.getDormitoryId(), dormitory.getName(), dormitory.getAddress(), dormitory.getTotalRooms()))
                .toList();
    }

    @GetMapping("/{id}")
    public Optional<DormitoryDto> getDormitoryById(@PathVariable Long id) {
        return dormitoryService.getDormitoryById(id)
                .map(dormitory -> new DormitoryDto(dormitory.getDormitoryId(), dormitory.getName(), dormitory.getAddress(), dormitory.getTotalRooms()));
    }

    @PostMapping
    public Dormitory createDormitory(@RequestBody CreateDormitoryRequest request) {
        Dormitory dormitory = new Dormitory();
        dormitory.setName(request.name());
        dormitory.setAddress(request.address());
        dormitory.setTotalRooms(request.totalRooms());
        return dormitoryService.createDormitory(dormitory);
    }

    @DeleteMapping("/{id}")
    public void deleteDormitory(@PathVariable Long id) {
        dormitoryService.deleteDormitory(id);
    }
}
