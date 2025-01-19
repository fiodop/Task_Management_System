package com.taskmanagementsystem.service;

import com.taskmanagementsystem.common.exception.TaskNotFoundException;
import com.taskmanagementsystem.common.exception.UserNotFoundException;
import com.taskmanagementsystem.model.dto.CommentDto;
import com.taskmanagementsystem.model.dto.TaskDto;
import com.taskmanagementsystem.model.entity.AppUser;
import com.taskmanagementsystem.model.entity.Comment;
import com.taskmanagementsystem.repository.AppUserRepository;
import com.taskmanagementsystem.repository.CommentRepository;
import com.taskmanagementsystem.repository.TaskRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final AppUserRepository appUserRepository;

    @Transactional
    public CommentDto save(CommentDto commentDto) {
        commentRepository.save(convertToComment(commentDto));
        return commentDto;
    }


    private Comment convertToComment(CommentDto commentDto) {

        return Comment.builder()
                .comment(commentDto.getComment())
                .task(taskRepository
                        .findById(commentDto.getId())
                        .orElseThrow(() -> new TaskNotFoundException(String.format("Task with id %s not found", taskId)))
                )
                .author(appUserRepository
                        .findByUsername(commentDto.getAuthorUsername())
                        .orElseThrow(() -> new UserNotFoundException(String.format("User with id %s not found", commentDto.getAuthorUsername())))
                )
                .build();
    }
    @Transactional
    public void delete(long commentId) {
        commentRepository.deleteById(commentId);
    }
}
