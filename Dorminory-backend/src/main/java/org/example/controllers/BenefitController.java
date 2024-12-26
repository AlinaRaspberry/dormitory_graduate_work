package org.example.controllers;

import org.example.models.Benefit;
import org.example.models.dto.BenefitDto;
import org.example.models.requests.CreateBenefitRequest;
import org.example.services.BenefitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/benefits")
public class BenefitController {
    @Autowired
    private BenefitService benefitService;

    @GetMapping
    public List<BenefitDto> getAllBenefits() {
        return benefitService.getAllBenefits().stream()
                .map(benefit -> new BenefitDto(benefit.getBenefitId(), benefit.getName(), benefit.getDescription()))
                .toList();
    }

    @GetMapping("/{id}")
    public Optional<BenefitDto> getBenefitById(@PathVariable Long id) {
        return benefitService.getBenefitById(id)
                .map(benefit -> new BenefitDto(benefit.getBenefitId(), benefit.getName(), benefit.getDescription()));
    }

    @PostMapping
    public Benefit createBenefit(@RequestBody CreateBenefitRequest request) {
        Benefit benefit = new Benefit();
        benefit.setName(request.name());
        benefit.setDescription(request.description());
        return benefitService.createBenefit(benefit);
    }

    @DeleteMapping("/{id}")
    public void deleteBenefit(@PathVariable Long id) {
        benefitService.deleteBenefit(id);
    }
}
