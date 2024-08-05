package com.boot.repository;

import com.boot.entity.Task;
import com.boot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByUser(User user);
    List<Task> findAllByUser(User user);
    List<Task> findAllByExecutor(String executor);
}
