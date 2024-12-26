package org.example.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class StudentBenefit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentBenefitId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "benefit_id")
    private Benefit benefit;
}
