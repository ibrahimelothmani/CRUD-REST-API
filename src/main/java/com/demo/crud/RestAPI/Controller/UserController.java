package com.demo.crud.RestAPI.Controller;

import com.demo.crud.RestAPI.Entity.User;
import com.demo.crud.RestAPI.Exception.UserNotFoundException;
import com.demo.crud.RestAPI.Repository.UserInfo;
import com.demo.crud.RestAPI.Repository.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/users")
class UserController {
    private final UserRepository userRepository;

    UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    List<UserInfo> getUser() {
        return userRepository.findAllByOrderByCreatedAtDesc();
    }

    @GetMapping("/{id}")
    ResponseEntity<UserInfo> getUserById(@PathVariable Long id) {
        var user =
                userRepository.findUserById(id)
                        .orElseThrow(() -> new UserNotFoundException("User not found"));
        return ResponseEntity.ok((UserInfo) user);
    }

    record CreateUserPayload(
            @NotEmpty(message = "Title is required")
            String title,
            @NotEmpty(message = "Url is required")
            String url) {
    }

    @PostMapping
    ResponseEntity<Void> createUser(
            @Valid @RequestBody CreateUserPayload payload) {
        var user = new User();
        user.setTitle(payload.title());
        user.setUrl(payload.url());
        user.setCreatedAt(Instant.now());
        var saveduser = userRepository.save(user);
        var url = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .build(saveduser.getId());
        return ResponseEntity.created(url).build();
    }

    record UpdateUserPayload(
            @NotEmpty(message = "Title is required")
            String title,
            @NotEmpty(message = "Url is required")
            String url) {
    }

    @PutMapping("/{id}")
    ResponseEntity<Void> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserPayload payload) {
        var user =
                userRepository.findById(id)
                        .orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setTitle(payload.title());
        user.setUrl(payload.url());
        user.setUpdatedAt(Instant.now());
        userRepository.save(user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        var user =
                userRepository.findById(id)
                        .orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepository.delete(user);
    }

    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<Void> handle(UserNotFoundException e) {
        return ResponseEntity.notFound().build();
    }

}