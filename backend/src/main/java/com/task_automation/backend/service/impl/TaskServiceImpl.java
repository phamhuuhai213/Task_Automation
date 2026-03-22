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
    
    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Khong tim thay Task voi id: " + id));
    }

    @Override
    public List<Task> getActiveTasksByUserId(Long userId) {
        return taskRepository.findByUserId(userId).stream()
        .filter(task -> task.getStatus() != com.task_automation.backend.enums.TaskStatus.BLOCKED)
        .toList();
    }
    
    @Override
    public Task updateTask(Long id, Task taskDetails) {
        Task task = getTaskById(id);
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());
        task.setPriority(taskDetails.getPriority());
        task.setImportance(taskDetails.getImportance());
        task.setDeadline(taskDetails.getDeadline());
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);        
    }
    
}
