package com.taskmanagementsystem.controller;

import com.taskmanagementsystem.model.dto.CommentDto;
import com.taskmanagementsystem.model.dto.TaskDto;
import com.taskmanagementsystem.service.CommentService;
import com.taskmanagementsystem.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ResponseEntity.ok(taskService.update(taskDto));
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
            summary = "Delete task",
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


    @Operation(
            summary = "Delete comment",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Comment deleted")
            }
    )
    public ResponseEntity<String> deleteComment(
            @PathVariable long commentId
    ){
        commentService.delete(commentId);
        return ResponseEntity.ok("Deleted");
    }
    @Operation(
            summary = "Get tasks by author username, num and size of page",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", description = "Request contains incorrect data")
            }
    )
    @GetMapping("/tasks-by-author")
    public ResponseEntity<List<TaskDto>> getTasksByAuthor(
            @RequestParam String authorUsername,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(taskService
                .getTasksByAuthorUsername(authorUsername, (PageRequest.of(page, size))));
    }

    @Operation(
            summary = "Get tasks by assignee username, num and size page",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", description = "Request contains incorrect data")
            }
    )
    @GetMapping("/tasks-by-assignee")
    public ResponseEntity<List<TaskDto>> getTasksByAssignee(
            @RequestParam String assigneeUsername,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ){
        return ResponseEntity.ok(taskService
                .getTasksByAssigneeUsername(assigneeUsername, PageRequest.of(page, size)));
    }


}
