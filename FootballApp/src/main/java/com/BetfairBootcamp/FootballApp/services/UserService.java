package com.BetfairBootcamp.FootballApp.services;

import com.BetfairBootcamp.FootballApp.dtos.UserDTO;
import com.BetfairBootcamp.FootballApp.entities.User;
import com.BetfairBootcamp.FootballApp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDTO registerUser(User user) {
        User savedUser = userRepository.save(user);
        return mapToDTO(savedUser);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    private UserDTO mapToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        return userDTO;
    }
}
