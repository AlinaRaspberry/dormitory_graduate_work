package org.example.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Dormitory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dormitoryId;

    private String name;

    private String address;

    private Integer totalRooms;

    @OneToMany(mappedBy = "dormitory", cascade = CascadeType.ALL)
    private List<Room> rooms;
}

