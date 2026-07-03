package com.creatordeal.creatorservice.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "creators")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Creator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, length = 100)
    private String userId;

    @Column(name = "display_name", nullable = false, length = 150)
    private String displayName;

    @Column(length = 100)
    private String country;

    @Column(name = "main_platform", length = 50)
    private String mainPlatform;

    @Column(name = "instagram_handle", length = 100)
    private String instagramHandle;

    @Column(name = "tiktok_handle", length = 100)
    private String tiktokHandle;

    @Column(name = "youtube_handle", length = 100)
    private String youtubeHandle;

    @Column(name = "followers_count")
    private Long followersCount;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}