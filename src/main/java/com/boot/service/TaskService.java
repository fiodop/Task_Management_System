package com.boot.service;

import com.boot.entity.Task;
import com.boot.entity.User;
import com.boot.repository.TaskRepository;
import com.boot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final UserService userService;
    private final TaskRepository taskRepository;

    /**
     * Receiving tasks by username
     *
     * @param username username
     * @return list of tasks
     */
    public List<Task> getTasksByUsername(String username) {
        var user = userService.getByUsername(username);

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
        var user = userService.getByUsername(username);
        task.setUser(user);
        taskRepository.save(task);
    }
}
