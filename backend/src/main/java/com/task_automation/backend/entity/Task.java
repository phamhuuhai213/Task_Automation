package com.task_automation.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.task_automation.backend.enums.TaskImportance;
import com.task_automation.backend.enums.TaskPriority;
import com.task_automation.backend.enums.TaskStatus;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="user_id" , nullable = false)
    private Long user_Id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "Text")
    private String desciption;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private TaskStatus status = TaskStatus.TODO;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskPriority priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskImportance importance;

    @Column(nullable = false)
    private LocalDateTime deadLine;

    @Column(name = "is_notified")
    @Builder.Default
    private boolean isNotified = false;

    @Column(name = "blockedAt")
    private LocalDateTime blockedAt;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", updatable = true)
    private LocalDateTime updatedAt;





    
   
}
