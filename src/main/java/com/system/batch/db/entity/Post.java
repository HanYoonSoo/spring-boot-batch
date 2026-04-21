package com.system.batch.db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
@NamedQuery(
        name = "Post.findByReportsReportedAtBetween",
        query = "SELECT distinct p FROM Post p JOIN FETCH p.reports r WHERE r.reportedAt >= :startDateTime AND r.reportedAt < :endDateTime"
)
@Getter
@Setter
public class Post {
    @Id
    private Long id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime blockedAt;  // 차단 일시 필드 추가

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    @BatchSize(size = 5)
    private List<Report> reports = new ArrayList<>();
}
