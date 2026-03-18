package com.task_automation.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import com.task_automation.backend.entity.Task;
import com.task_automation.backend.enums.TaskStatus;


@Repository
public interface TaskRepository extends JpaRepository<Task , Long> {
    List<Task> findByUser_Id(Long user_Id);
    List<Task> findfindByStatusNotAndDeadlineBefore(TaskStatus status, LocalDateTime now);
    List<Task> findByStatusAndIsNotifiedFalseAndDeadlineBetween(
        TaskStatus status, LocalDateTime start, LocalDateTime end
    );
}
