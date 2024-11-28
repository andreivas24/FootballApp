package com.BetfairBootcamp.FootballApp.controllers;

import com.BetfairBootcamp.FootballApp.dtos.UserDTO;
import com.BetfairBootcamp.FootballApp.entities.User;
import com.BetfairBootcamp.FootballApp.repositories.UserRepository;
import com.BetfairBootcamp.FootballApp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        System.out.println("Received PUT request for ID: " + id);
        System.out.println("Payload: " + userDTO);

        UserDTO updatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User with ID " + id + " has been deleted.";
    }

}
