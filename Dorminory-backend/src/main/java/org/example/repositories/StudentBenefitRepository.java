package org.example.repositories;

import org.example.models.StudentBenefit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentBenefitRepository extends JpaRepository<StudentBenefit, Long> {
}
