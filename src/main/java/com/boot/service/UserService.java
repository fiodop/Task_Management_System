package com.boot.service;

import com.boot.entity.User;
import com.boot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    /**
     * Saving user
     *
     * @param user user
     * @return saved user
     */
    public User save(User user){
        return userRepository.save(user);
    }


    /**
     * Creating user
     *
     * @param user user
     * @return created user
     */
    public User create(User user){
        if(userRepository.existsByUsername(user.getUsername())){
            throw new RuntimeException("User with this username exist");
        }

        if (userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("User with this email is exist");
        }

        return save(user);
    }

    /**
     * Getting user by username
     *
     * @param username username
     * @return user
     */
    public User getByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    /**
     * Getting user by username
     *
     * need for Sring security
     * @return user
     */
    public UserDetailsService userDetailsService(){
        return this::getByUsername;
    }
    public User getCurrentUser(){
        var username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return getByUsername(username);
    }
}
