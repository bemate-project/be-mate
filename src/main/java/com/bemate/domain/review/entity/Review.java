package com.bemate.domain.review.entity;

import jakarta.persistence.Entity;

@Entity
public class Review {
    private Long id;
    private String title;
    private String content;
    private String imageFolder;
    private String imageFiles;
}
