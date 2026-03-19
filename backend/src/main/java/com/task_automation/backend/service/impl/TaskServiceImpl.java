package com.task_automation.backend.service.impl;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

import com.task_automation.backend.entity.Task;
import com.task_automation.backend.repository.TaskRepository;
import com.task_automation.backend.service.TaskService;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTaskByUserId(Long userId) {
        return taskRepository.findByUserId(userId);
    }

    @Override
    public int calculatePriorityLevel(Task task) {
        int pScore = switch (task.getPriority()){
            case HIGH -> 3;
            case MEDIUM -> 2;
            case LOW -> 1;
        };

        int iScore = switch (task.getImportance()){
            case HARD -> 3;
            case NORMAL -> 2;
            case EASY -> 1;
        };

        return (3 - pScore)*3 + (4 - iScore);
    }
}
