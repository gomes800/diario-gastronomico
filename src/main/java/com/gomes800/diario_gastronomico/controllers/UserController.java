package com.gomes800.diario_gastronomico.controllers;

import com.gomes800.diario_gastronomico.domain.User;
import com.gomes800.diario_gastronomico.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public List<User> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        Optional<User> user = service.findByID(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/insert")
    public ResponseEntity<User> insertUser(@RequestBody User user) {
        User newUser = service.insert(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        Optional<User> existingUser = service.findByID(id);
        if (existingUser.isPresent()) {
            User updateUser = service.update(id, user);
            return ResponseEntity.ok(updateUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (service.existById(id)) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
