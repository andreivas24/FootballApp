package com.BetfairBootcamp.FootballApp.repositories;

import com.BetfairBootcamp.FootballApp.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
}
