package com.BetfairBootcamp.FootballApp.repositories;

import com.BetfairBootcamp.FootballApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
}
