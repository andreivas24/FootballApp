package com.BetfairBootcamp.FootballApp.services;

import com.BetfairBootcamp.FootballApp.dtos.UserDTO;
import com.BetfairBootcamp.FootballApp.entities.User;
import com.BetfairBootcamp.FootballApp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDTO registerUser(User user) {
        User savedUser = userRepository.save(user);
        return mapToDTO(savedUser);
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        return mapToDTO(user);
    }

    public UserDTO mapToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());

        User savedUser = userRepository.save(user);

        return mapToDTO(savedUser);
    }


    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with ID: " + id);
        }

        userRepository.deleteById(id);
    }
}
