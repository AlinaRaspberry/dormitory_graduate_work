package org.example.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    private String name;

    private Double averageScore;

    private Date dateOfBirth;

    private Date createdAt;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<StudentBenefit> benefits;
}
