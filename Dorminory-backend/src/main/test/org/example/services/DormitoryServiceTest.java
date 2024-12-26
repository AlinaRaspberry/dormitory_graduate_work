package org.example.services;

import org.example.models.Dormitory;
import org.example.repositories.DormitoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DormitoryServiceTest {

    @Mock
    private DormitoryRepository dormitoryRepository;

    @InjectMocks
    private DormitoryService dormitoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllDormitories_ShouldReturnAllDormitories() {
        Dormitory dormitory1 = new Dormitory();
        Dormitory dormitory2 = new Dormitory();
        when(dormitoryRepository.findAll()).thenReturn(List.of(dormitory1, dormitory2));

        List<Dormitory> result = dormitoryService.getAllDormitories();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(dormitoryRepository, times(1)).findAll();
    }

    @Test
    void getDormitoryById_ShouldReturnDormitory_WhenFound() {
        Dormitory dormitory = new Dormitory();
        dormitory.setDormitoryId(1L);
        when(dormitoryRepository.findById(1L)).thenReturn(Optional.of(dormitory));

        Optional<Dormitory> result = dormitoryService.getDormitoryById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getDormitoryId());
        verify(dormitoryRepository, times(1)).findById(1L);
    }

    @Test
    void getDormitoryById_ShouldReturnEmpty_WhenNotFound() {
        when(dormitoryRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Dormitory> result = dormitoryService.getDormitoryById(1L);

        assertFalse(result.isPresent());
        verify(dormitoryRepository, times(1)).findById(1L);
    }

    @Test
    void createDormitory_ShouldSaveAndReturnDormitory() {
        Dormitory dormitory = new Dormitory();
        Dormitory savedDormitory = new Dormitory();
        savedDormitory.setDormitoryId(1L);
        when(dormitoryRepository.save(dormitory)).thenReturn(savedDormitory);

        Dormitory result = dormitoryService.createDormitory(dormitory);

        assertNotNull(result);
        assertEquals(1L, result.getDormitoryId());
        verify(dormitoryRepository, times(1)).save(dormitory);
    }

    @Test
    void deleteDormitory_ShouldRemoveDormitoryById() {
        doNothing().when(dormitoryRepository).deleteById(1L);

        dormitoryService.deleteDormitory(1L);

        verify(dormitoryRepository, times(1)).deleteById(1L);
    }
}
