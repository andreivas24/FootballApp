package com.BetfairBootcamp.FootballApp.repositories;

import com.BetfairBootcamp.FootballApp.entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
