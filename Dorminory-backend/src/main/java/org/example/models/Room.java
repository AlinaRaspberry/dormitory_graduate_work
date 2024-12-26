package org.example.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @ManyToOne
    @JoinColumn(name = "dormitory_id")
    private Dormitory dormitory;

    private String roomNumber;

    private Integer capacity;

    private Integer occupiedBeds;
}
