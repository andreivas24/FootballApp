package com.BetfairBootcamp.FootballApp.repositories;

import com.BetfairBootcamp.FootballApp.entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Participant p WHERE p.user.id = :userId AND p.match.id = :matchId")
    boolean existsByUserAndMatchId(@Param("userId") Long userId, @Param("matchId") Long matchId);
}
