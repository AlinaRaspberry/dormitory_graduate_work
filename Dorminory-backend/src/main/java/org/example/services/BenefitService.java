package org.example.services;

import org.example.models.Benefit;
import org.example.repositories.BenefitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BenefitService {
    @Autowired
    private BenefitRepository benefitRepository;

    public List<Benefit> getAllBenefits() {
        return benefitRepository.findAll();
    }

    public Optional<Benefit> getBenefitById(Long id) {
        return benefitRepository.findById(id);
    }

    public Benefit createBenefit(Benefit benefit) {
        return benefitRepository.save(benefit);
    }

    public void deleteBenefit(Long id) {
        benefitRepository.deleteById(id);
    }
}
