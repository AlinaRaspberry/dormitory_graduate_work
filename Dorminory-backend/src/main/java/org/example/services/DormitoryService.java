package org.example.services;

import org.example.models.Dormitory;
import org.example.repositories.DormitoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DormitoryService {
    @Autowired
    private DormitoryRepository dormitoryRepository;

    public List<Dormitory> getAllDormitories() {
        return dormitoryRepository.findAll();
    }

    public Optional<Dormitory> getDormitoryById(Long id) {
        return dormitoryRepository.findById(id);
    }

    public Dormitory createDormitory(Dormitory dormitory) {
        return dormitoryRepository.save(dormitory);
    }

    public void deleteDormitory(Long id) {
        dormitoryRepository.deleteById(id);
    }
}
