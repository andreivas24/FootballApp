package com.BetfairBootcamp.FootballApp.controllers;

import com.BetfairBootcamp.FootballApp.dtos.UserDTO;
import com.BetfairBootcamp.FootballApp.entities.User;
import com.BetfairBootcamp.FootballApp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserPageController {

    private final UserService userService;

    @GetMapping("/page")
    public String getUsersPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String name,
                               @RequestParam String email,
                               @RequestParam String password,
                               @RequestParam String role) {
        User user = User.builder()
                .name(name)
                .email(email)
                .password(password)
                .role(role)
                .build();
        userService.registerUser(user);

        return "redirect:/users/page";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return "redirect:/users/page";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam Long id,
                             @RequestParam String name,
                             @RequestParam String email,
                             @RequestParam String role) {
        System.out.println("ID Received: " + id);
        System.out.println("Name Received: " + name);
        System.out.println("Email Received: " + email);
        System.out.println("Role Received: " + role);

        UserDTO userDTO = new UserDTO();
        userDTO.setName(name);
        userDTO.setEmail(email);
        userDTO.setRole(role);

        userService.updateUser(id, userDTO);

        return "redirect:/users/page";
    }
}

