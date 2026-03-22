package com.task_automation.backend.service;

import com.task_automation.backend.entity.Task;
import java.util.List;

public interface TaskService {
    Task createTask(Task task);
    List<Task> getAllTaskByUserId(Long userId);
    int calculatePriorityLevel(Task task);

    Task getTaskById(Long id);
    Task updateTask(Long id, Task taskDetails);
    void deleteTask(Long id);
    List<Task> getActiveTasksByUserId(Long userId);

}
