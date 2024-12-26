package org.example.services;

import org.example.models.Benefit;
import org.example.repositories.BenefitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BenefitServiceTest {

    @Mock
    private BenefitRepository benefitRepository;

    @InjectMocks
    private BenefitService benefitService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllBenefits_ShouldReturnAllBenefits() {
        Benefit benefit1 = new Benefit();
        Benefit benefit2 = new Benefit();
        when(benefitRepository.findAll()).thenReturn(List.of(benefit1, benefit2));

        List<Benefit> result = benefitService.getAllBenefits();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(benefitRepository, times(1)).findAll();
    }

    @Test
    void getBenefitById_ShouldReturnBenefit_WhenFound() {
        Benefit benefit = new Benefit();
        benefit.setBenefitId(1L);
        when(benefitRepository.findById(1L)).thenReturn(Optional.of(benefit));

        Optional<Benefit> result = benefitService.getBenefitById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getBenefitId());
        verify(benefitRepository, times(1)).findById(1L);
    }

    @Test
    void getBenefitById_ShouldReturnEmpty_WhenNotFound() {
        when(benefitRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Benefit> result = benefitService.getBenefitById(1L);

        assertFalse(result.isPresent());
        verify(benefitRepository, times(1)).findById(1L);
    }

    @Test
    void createBenefit_ShouldSaveAndReturnBenefit() {
        Benefit benefit = new Benefit();
        Benefit savedBenefit = new Benefit();
        savedBenefit.setBenefitId(1L);
        when(benefitRepository.save(benefit)).thenReturn(savedBenefit);

        Benefit result = benefitService.createBenefit(benefit);

        assertNotNull(result);
        assertEquals(1L, result.getBenefitId());
        verify(benefitRepository, times(1)).save(benefit);
    }

    @Test
    void deleteBenefit_ShouldRemoveBenefitById() {
        doNothing().when(benefitRepository).deleteById(1L);

        benefitService.deleteBenefit(1L);

        verify(benefitRepository, times(1)).deleteById(1L);
    }
}
