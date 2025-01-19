package com.taskmanagementsystem.controller;

import com.taskmanagementsystem.model.dto.CommentDto;
import com.taskmanagementsystem.model.dto.TaskDto;
import com.taskmanagementsystem.service.CommentService;
import com.taskmanagementsystem.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "admin controller api")
@RestController
@RequestMapping("/api/v1/task-management-system/admin")
@RequiredArgsConstructor
public class AdminController {
    private final TaskService taskService;
    private final CommentService commentService;

    @Operation(
            summary = "Create task",
            responses = {
                    @ApiResponse(responseCode = "200", description = "task successfully created"),
                    @ApiResponse(responseCode = "400", description = "Request contains incorrect data")
            }
    )
    @PostMapping("/create-task")
    public ResponseEntity<TaskDto> createTask(
            @RequestBody TaskDto taskDto
    ) {
        return ResponseEntity.ok(taskService.save(taskDto));
    }

    @Operation(
            summary = "Update task",
            responses = {
                    @ApiResponse(responseCode = "200", description = "task successfully updated"),
                    @ApiResponse(responseCode = "400", description = "Request contains incorrect data")
            }
    )
    @PatchMapping("/{taskId}")
    public ResponseEntity<TaskDto> updateTask(
            @PathVariable long taskId,
            @RequestBody TaskDto taskDto
    ){
        return ResponseEntity.ok(taskService.update(taskId, taskDto));
    }

    @Operation(
            summary = "Add comment to task",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Comment successfully added"),
                    @ApiResponse(responseCode = "400", description = "Request contains incorrect data")
            }
    )
    @PostMapping("/comment")
    public ResponseEntity<CommentDto> comment(
            @RequestBody CommentDto commentDto
            ){
        return ResponseEntity.ok(commentService.save(commentDto));
    }

    @Operation(
            summary = "delete task",
            responses = {
                    @ApiResponse(responseCode = "200", description = "task successfully deleted"),
                    @ApiResponse(responseCode = "400", description = "Request contains incorrect data")
            }
    )
    @DeleteMapping("/delete-task/{taskId}")
    public ResponseEntity<String> deleteTask(
            @PathVariable long taskId
    ){
        taskService.delete(taskId);
        return ResponseEntity.ok("Deleted");
    }

    public ResponseEntity<String> deleteComment(
            @PathVariable long commentId
    ){
        commentService.delete(commentId);
        return ResponseEntity.ok("Deleted");
    }

}
