package com.boot.controller;

import com.boot.entity.Task;
import com.boot.service.JwtService;
import com.boot.service.TaskService;
import com.boot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tasks")
@RequiredArgsConstructor
public class MainController {
    private final TaskService taskService;
    private final UserService userService;
    private final JwtService jwtService;

    @GetMapping("/my-tasks")
    public ResponseEntity<List<Task>> tasks(@RequestHeader(value = "Authorization") String header){
        if(header != null && header.startsWith("Bearer ")){
            var token = header.substring(7);
            try {
                var username = jwtService.extractUsername(token);
                List<Task> tasks = taskService.getTasksByUsername(username);
                return ResponseEntity.ok(tasks);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/my-tasks-to-execute")
    public ResponseEntity<List<Task>> tasksToExecute(@RequestHeader(value = "Authorization") String header){
        if(header != null && header.startsWith("Bearer ")){
            var token = header.substring(7);

            try {
                var username = jwtService.extractUsername(token);
                List<Task> tasksToExecute = taskService.getTasksByExecutor(username);
                return ResponseEntity.ok(tasksToExecute);
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/add-task")
    public void addTask(@RequestHeader(value = "Authorization") String header, @RequestBody Task task){
        if(header != null && header.startsWith("Bearer ")){
            var token = header.substring(7);

            try {
                var username = jwtService.extractUsername(token);
                taskService.add(task, username);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @PostMapping("/edit-task/{taskId}")
    public void editTask(@RequestHeader(value = "Authorization") String header, @PathVariable Long taskId, Task task){
        if(header != null && header.startsWith("Bearer ")){
            var token = header.substring(7);

            try {
                var username = jwtService.extractUsername(token);
                if(username.equals(task.getUser().getUsername())) {
                    taskService.add(task, username);
                } else {
                    ;
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }

    }


}
