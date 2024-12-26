package org.example.services;

import org.example.models.AllocationPlan;
import org.example.repositories.AllocationPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AllocationPlanService {
    @Autowired
    private AllocationPlanRepository allocationPlanRepository;

    public List<AllocationPlan> getAllAllocations() {
        return allocationPlanRepository.findAll();
    }

    public Optional<AllocationPlan> getAllocationById(Long id) {
        return allocationPlanRepository.findById(id);
    }

    public AllocationPlan createAllocation(AllocationPlan allocationPlan) {
        return allocationPlanRepository.save(allocationPlan);
    }

    public void deleteAllocation(Long id) {
        allocationPlanRepository.deleteById(id);
    }
}
