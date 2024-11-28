package com.BetfairBootcamp.FootballApp.repositories;

import com.BetfairBootcamp.FootballApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);

    @Query("SELECT u FROM User u WHERE u.role = :role")
    List<User> findAllByRole(@Param("role") String role);
}
