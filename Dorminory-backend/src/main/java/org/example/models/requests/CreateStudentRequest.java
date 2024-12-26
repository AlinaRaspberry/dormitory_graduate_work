package org.example.models.requests;

import java.util.Date;
import java.util.List;

public record CreateStudentRequest(String name, Double averageScore, Date dateOfBirth, List<Long> benefitIds) {
}
