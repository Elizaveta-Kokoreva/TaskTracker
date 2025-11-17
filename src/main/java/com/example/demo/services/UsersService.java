package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.repos.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public User getById (Long id) {
        return usersRepository.getById(id);
    }
}
