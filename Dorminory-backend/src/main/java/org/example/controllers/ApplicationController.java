package org.example.controllers;

import org.example.models.Application;
import org.example.models.dto.ApplicationDto;
import org.example.models.requests.CreateApplicationRequest;
import org.example.services.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/applications")
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    @GetMapping
    public List<ApplicationDto> getAllApplications() {
        return applicationService.getAllApplications().stream()
                .map(application -> new ApplicationDto(application.getApplicationId(), application.getStudent().getStudentId(), application.getDormitory().getDormitoryId(), application.getStatus()))
                .toList();
    }

    @GetMapping("/{id}")
    public Optional<ApplicationDto> getApplicationById(@PathVariable Long id) {
        return applicationService.getApplicationById(id)
                .map(application -> new ApplicationDto(application.getApplicationId(), application.getStudent().getStudentId(), application.getDormitory().getDormitoryId(), application.getStatus()));
    }

    @PostMapping
    public Application createApplication(@RequestBody CreateApplicationRequest request) {
        Application application = new Application();
        application.setStatus(request.status());
        return applicationService.createApplication(application);
    }

    @DeleteMapping("/{id}")
    public void deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplication(id);
    }
}
