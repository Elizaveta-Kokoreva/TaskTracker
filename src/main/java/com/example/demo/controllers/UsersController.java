package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getById (@PathVariable Long id) {
        User u = usersService.getById(id);

        if(u == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(u);
    }
}
