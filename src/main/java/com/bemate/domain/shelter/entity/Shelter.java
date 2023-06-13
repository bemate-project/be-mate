package com.bemate.domain.shelter.entity;

import jakarta.persistence.*;

@Entity
public class Shelter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shelter_no")
    private Long id;
    private String name;
}
