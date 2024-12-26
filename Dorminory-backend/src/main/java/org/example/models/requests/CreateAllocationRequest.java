package org.example.models.requests;

import java.util.Date;

public record CreateAllocationRequest(Long roomId, Long studentId, Date allocationDate) {
}
