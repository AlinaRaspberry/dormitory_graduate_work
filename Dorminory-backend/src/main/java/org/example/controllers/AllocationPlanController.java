package org.example.controllers;

import org.example.models.AllocationPlan;
import org.example.models.dto.AllocationPlanDto;
import org.example.models.requests.CreateAllocationRequest;
import org.example.services.AllocationPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/allocations")
public class AllocationPlanController {
    @Autowired
    private AllocationPlanService allocationPlanService;

    @GetMapping
    public List<AllocationPlanDto> getAllAllocations() {
        return allocationPlanService.getAllAllocations().stream()
                .map(allocation -> new AllocationPlanDto(allocation.getAllocationId(), allocation.getRoom().getRoomId(), allocation.getStudent().getStudentId(), allocation.getAllocationDate()))
                .toList();
    }

    @GetMapping("/{id}")
    public Optional<AllocationPlanDto> getAllocationById(@PathVariable Long id) {
        return allocationPlanService.getAllocationById(id)
                .map(allocation -> new AllocationPlanDto(allocation.getAllocationId(), allocation.getRoom().getRoomId(), allocation.getStudent().getStudentId(), allocation.getAllocationDate()));
    }

    @PostMapping
    public AllocationPlan createAllocation(@RequestBody CreateAllocationRequest request) {
        AllocationPlan allocationPlan = new AllocationPlan();
        allocationPlan.setAllocationDate(request.allocationDate());
        return allocationPlanService.createAllocation(allocationPlan);
    }

    @DeleteMapping("/{id}")
    public void deleteAllocation(@PathVariable Long id) {
        allocationPlanService.deleteAllocation(id);
    }
}
