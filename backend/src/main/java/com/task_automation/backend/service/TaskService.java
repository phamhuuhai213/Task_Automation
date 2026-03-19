package com.task_automation.backend.service;

import com.task_automation.backend.entity.Task;
import java.util.List;

public interface TaskService {
    Task createTask(Task task);
    List<Task> getAllTaskByUserId(Long userId);
    int calculatePriorityLevel(Task task);
}
