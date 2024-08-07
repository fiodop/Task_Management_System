package com.boot.repository;

import com.boot.entity.AppUser;
import com.boot.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByUser(AppUser appUser);
    List<Task> findAllByUser(AppUser appUser);
    List<Task> findAllByExecutor(String executor);
}
