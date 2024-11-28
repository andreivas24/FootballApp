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

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipantService {

    private final ParticipantRepository participantRepository;
    private final UserRepository userRepository;
    private final MatchRepository matchRepository;

    public ParticipantDTO addParticipant(String userName, Long newMatchId) {
        User user = userRepository.findByName(userName)
                .orElseThrow(() -> new RuntimeException("User not found with name: " + userName));

        if (newMatchId != null && participantRepository.existsByUserAndMatchId(user.getId(), newMatchId)) {
            throw new RuntimeException("Participant already exists for this user and match");
        }

        Match match = null;
        if (newMatchId != null) {
            match = matchRepository.findById(newMatchId)
                    .orElseThrow(() -> new RuntimeException("Match not found with ID: " + newMatchId));
        }

        Participant participant = Participant.builder()
                .user(user)
                .match(match)
                .build();

        Participant savedParticipant = participantRepository.save(participant);
        return mapToDTO(savedParticipant);
    }

    public ParticipantDTO assignPlayerToMatch(Long participantId, Long matchId) {
        Participant participant = participantRepository.findById(participantId)
                .orElseThrow(() -> new RuntimeException("Participant not found with ID: " + participantId));

        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new RuntimeException("Match not found with ID: " + matchId));

        if (match.getCurrentPlayers() >= match.getMaxPlayers()) {
            throw new RuntimeException("Match is already full!");
        }

        participant.setMatch(match);
        match.setCurrentPlayers(match.getCurrentPlayers() + 1);

        match.notifyOrganizer("A new participant has joined the match!");

        if (match.getCurrentPlayers() == match.getMaxPlayers()) {
            match.notifyOrganizer("The match is now full!");
        }

        participantRepository.save(participant);
        matchRepository.save(match);

        return mapToDTO(participant);
    }

    public ParticipantDTO mapToDTO(Participant participant) {
        ParticipantDTO dto = new ParticipantDTO();
        dto.setId(participant.getId());
        dto.setUserName(participant.getUser() != null ? participant.getUser().getName() : "Unknown User");
        dto.setMatchId(participant.getMatch() != null ? participant.getMatch().getId() : null);
        return dto;
    }

    public void deleteParticipant(Long id) {
        if (!participantRepository.existsById(id)) {
            throw new RuntimeException("Participant not found with ID: " + id);
        }
        participantRepository.deleteById(id);
    }

    public ParticipantDTO updateParticipant(Long id, ParticipantDTO participantDTO) {
        Participant participant = participantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Participant not found with ID: " + id));

        User user = userRepository.findByName(participantDTO.getUserName())
                .orElseThrow(() -> new RuntimeException("User not found with name: " + participantDTO.getUserName()));
        participant.setUser(user);

        if (participantDTO.getMatchId() != null) {
            Match match = matchRepository.findById(participantDTO.getMatchId())
                    .orElseThrow(() -> new RuntimeException("Match not found with ID: " + participantDTO.getMatchId()));
            participant.setMatch(match);
        } else {
            participant.setMatch(null);
        }

        Participant updatedParticipant = participantRepository.save(participant);
        return mapToDTO(updatedParticipant);
    }

    public ParticipantDTO getParticipantById(Long id) {
        Participant participant = participantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Participant not found with ID: " + id));
        return mapToDTO(participant);
    }

    public List<ParticipantDTO> getAllParticipants() {
        return participantRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }
}
