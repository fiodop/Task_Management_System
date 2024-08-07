package com.boot.service;

import com.boot.entity.Task;
import com.boot.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final AppUserService appUserService;
    private final TaskRepository taskRepository;

    /**
     * Receiving tasks by username
     *
     * @param username username
     * @return list of tasks
     */
    public List<Task> getTasksByUsername(String username) {
        var user = appUserService.getByUsername(username);

        return taskRepository.findAllByUser(user);
    }

    /**
     * Receiving tasks by executor
     *
     * @param executor executor
     * @return list of tasks
     */
    public List<Task> getTasksByExecutor(String executor) {
        return taskRepository.findAllByExecutor(executor);
    }

    public void add(Task task, String username) {
        var user = appUserService.getByUsername(username);
        task.setAppUser(user);
        taskRepository.save(task);
    }
}
