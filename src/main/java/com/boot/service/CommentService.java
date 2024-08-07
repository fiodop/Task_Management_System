package com.boot.service;

import com.boot.entity.Comment;
import com.boot.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    /**
     * Add comment
     *
     * @param comment comment
     */
    public void add(Comment comment){
        commentRepository.save(comment);
    }
}
