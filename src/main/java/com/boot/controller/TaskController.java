package com.boot.controller;

import com.boot.entity.Comment;
import com.boot.entity.Task;
import com.boot.service.CommentService;
import com.boot.service.JwtService;
import com.boot.service.TaskService;
import com.boot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(value = "TaskController", description = "Operations pertaining to tasks in the Task Management System")
@RestController
@RequestMapping("tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;
    private final JwtService jwtService;
    private final CommentService commentService;

    @ApiOperation(value = "View list of alaivable user's tasks", response = List.class)
    @GetMapping("/my-tasks")
    public ResponseEntity<List<Task>> tasks(@RequestHeader(value = "Authorization") String header){
        if(header != null && header.startsWith("Bearer ")){
            var token = header.substring(7);
            var username = jwtService.extractUsername(token);
            UserDetails userDetails = userService.getByUsername(username);
            if(jwtService.isTokenValid(token, userDetails)){
            try {
                List<Task> tasks = taskService.getTasksByUsername(username);
                return ResponseEntity.ok(tasks);
            }catch (Exception e){
                e.printStackTrace();
            }
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ApiOperation(value = "View user's list to execute tasks", response = List.class)
    @GetMapping("/my-tasks-to-execute")
    public ResponseEntity<List<Task>> tasksToExecute(@RequestHeader(value = "Authorization") String header){
        if(header != null && header.startsWith("Bearer ")) {
            var token = header.substring(7);
            var username = jwtService.extractUsername(token);
            UserDetails userDetails = userService.getByUsername(username);
            if (jwtService.isTokenValid(token, userDetails)) {
                try {
                    List<Task> tasksToExecute = taskService.getTasksByExecutor(username);
                    return ResponseEntity.ok(tasksToExecute);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ApiOperation(value = "addind task")
    @PostMapping("/add-task")
    public void addTask(@RequestHeader(value = "Authorization") String header, @RequestBody Task task){
        if(header != null && header.startsWith("Bearer ")) {
            var token = header.substring(7);
            var username = jwtService.extractUsername(token);
            UserDetails userDetails = userService.getByUsername(username);
            if (jwtService.isTokenValid(token, userDetails)) {
                try {
                    taskService.add(task, username);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @ApiOperation(value = "Editing tasks")
    @PostMapping("/edit-task/{taskId}")
    public void editTask(@RequestHeader(value = "Authorization") String header, @PathVariable long taskId, @RequestBody Task task){
        if(header != null && header.startsWith("Bearer ")){
            var token = header.substring(7);
            var username = jwtService.extractUsername(token);
            UserDetails userDetails = userService.getByUsername(username);
            if(jwtService.isTokenValid(token, userDetails)) {
                try {
                    if (username.equals(task.getUser().getUsername())) {
                        taskService.add(task, username);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @ApiOperation(value = "checking other user's tasks")
    @GetMapping("/{username}")
    public ResponseEntity<List<Task>> seeTasks(@RequestHeader(value = "Authorization") String header,
                                               @PathVariable String username){
        if(header != null && header.startsWith("Bearer ")){
            var token = header.substring(7);
            var usernameDetails = jwtService.extractUsername(token);
            UserDetails userDetails = userService.getByUsername(usernameDetails);
            if(jwtService.isTokenValid(token, userDetails)) {
                try {
                    List<Task> tasks = taskService.getTasksByUsername(username);
                    return ResponseEntity.ok(tasks);
                }catch (Exception e){
                    e.printStackTrace();
                }
                }
            }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ApiOperation(value = "adding comment to task")
    @PostMapping("/add-comment/{taskId}")
    public void addComment(@RequestHeader(value = "Authorization")String header,
                           @PathVariable long taskId, @RequestBody Comment comment){
        if(header != null && header.startsWith("Bearer "));
        var token = header.substring(7);
        var username = jwtService.extractUsername(token);
        UserDetails userDetails = userService.getByUsername(username);
        if(jwtService.isTokenValid(token, userDetails)) {
            try {
                commentService.add(comment);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




}
