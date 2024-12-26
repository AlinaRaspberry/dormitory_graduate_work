package org.example.repositories;

import org.example.models.AllocationPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllocationPlanRepository extends JpaRepository<AllocationPlan, Long> {
}
