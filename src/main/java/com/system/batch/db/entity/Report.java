package com.system.batch.db.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
@Getter
public class Report {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private String reportType;
    private int reporterLevel;
    private String evidenceData;
    private LocalDateTime reportedAt;
}
