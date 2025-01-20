package com.taskmanagementsystem.service;

import com.taskmanagementsystem.model.entity.AppUser;
import com.taskmanagementsystem.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserService {
    private final AppUserRepository appUserRepository;

    public void deleteAppUser(String username) {
        appUserRepository.deleteByUsername(username);
    }
}
