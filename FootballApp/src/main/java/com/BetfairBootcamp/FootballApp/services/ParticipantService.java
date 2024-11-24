package com.BetfairBootcamp.FootballApp.services;

import com.BetfairBootcamp.FootballApp.dtos.ParticipantDTO;
import com.BetfairBootcamp.FootballApp.entities.Match;
import com.BetfairBootcamp.FootballApp.entities.Participant;
import com.BetfairBootcamp.FootballApp.entities.User;
import com.BetfairBootcamp.FootballApp.repositories.MatchRepository;
import com.BetfairBootcamp.FootballApp.repositories.ParticipantRepository;
import com.BetfairBootcamp.FootballApp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParticipantService {

    private final ParticipantRepository participantRepository;
    private final UserRepository userRepository;
    private final MatchRepository matchRepository;

    public ParticipantDTO addParticipant(ParticipantDTO participantDTO) {
        // Retrieve the User entity by userId
        User user = userRepository.findById(participantDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + participantDTO.getUserId()));

        // Create a Participant with the retrieved User
        Participant participant = Participant.builder()
                .user(user)
                .build();

        Participant savedParticipant = participantRepository.save(participant);

        return mapToDTO(savedParticipant);
    }

    public ParticipantDTO assignMatch(Long participantId, Long matchId) {
        Participant participant = participantRepository.findById(participantId)
                .orElseThrow(() -> new RuntimeException("Participant not found with ID: " + participantId));

        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new RuntimeException("Match not found with ID: " + matchId));

        participant.setMatch(match);
        Participant updatedParticipant = participantRepository.save(participant);

        return mapToDTO(updatedParticipant);
    }


    // Mapping to DTO
    private ParticipantDTO mapToDTO(Participant participant) {
        ParticipantDTO dto = new ParticipantDTO();
        dto.setId(participant.getId());
        dto.setUserId(participant.getUser().getId());
        if (participant.getMatch() != null) {
            dto.setMatchId(participant.getMatch().getId());
        }
        return dto;
    }
}
