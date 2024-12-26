package org.example.models.requests;

public record CreateApplicationRequest(Long studentId, Long dormitoryId, String status) {
}
