package com.BetfairBootcamp.FootballApp.services;

import com.BetfairBootcamp.FootballApp.dtos.MatchDTO;
import com.BetfairBootcamp.FootballApp.entities.Match;
import com.BetfairBootcamp.FootballApp.entities.User;
import com.BetfairBootcamp.FootballApp.repositories.MatchRepository;
import com.BetfairBootcamp.FootballApp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;
    private final UserRepository userRepository;

    public MatchDTO createMatch(Match match) {
        User organizer = userRepository.findById(match.getOrganizer().getId())
                .orElseThrow(() -> new RuntimeException("Organizer not found with ID: " + match.getOrganizer().getId()));

        match.setOrganizer(organizer);

        Match savedMatch = matchRepository.save(match);
        return mapToDTO(savedMatch);
    }

    public MatchDTO createMatchWithOrganizer(Match match, Long organizerId) {
        User organizer = userRepository.findById(organizerId)
                .orElseThrow(() -> new RuntimeException("Organizer not found with ID: " + organizerId));

        match.setOrganizer(organizer);

        Match savedMatch = matchRepository.save(match);
        return mapToDTO(savedMatch);
    }

    public List<MatchDTO> getAllMatches() {
        return matchRepository.findAll().stream()
                .map(match -> {
                    MatchDTO dto = new MatchDTO();
                    dto.setId(match.getId());
                    dto.setLocation(match.getLocation());
                    dto.setDateTime(match.getDateTime());
                    dto.setMaxPlayers(match.getMaxPlayers());
                    dto.setCurrentPlayers(match.getCurrentPlayers());
                    dto.setOrganizerName(match.getOrganizer() != null ? match.getOrganizer().getName() : "No Organizer");
                    dto.setParticipantNames(match.getParticipants().stream()
                            .map(participant -> participant.getUser().getName())
                            .collect(Collectors.toList()));
                    return dto;
                })
                .collect(Collectors.toList());
    }


    public Optional<Match> getMatchById(Long id) {
        return matchRepository.findById(id);
    }

    public MatchDTO updateMatch(Long id, Match updatedMatch) {
        return matchRepository.findById(id)
                .map(existingMatch -> {
                    existingMatch.setLocation(updatedMatch.getLocation());
                    existingMatch.setDateTime(updatedMatch.getDateTime());
                    existingMatch.setMaxPlayers(updatedMatch.getMaxPlayers());
                    existingMatch.setCurrentPlayers(updatedMatch.getCurrentPlayers());

                    Match savedMatch = matchRepository.save(existingMatch);
                    return mapToDTO(savedMatch);
                })
                .orElseThrow(() -> new RuntimeException("Match not found with id: " + id));
    }

    public void deleteMatch(Long id) {
        if (!matchRepository.existsById(id)) {
            throw new RuntimeException("Match not found with id: " + id);
        }
        matchRepository.deleteById(id);
    }

    public MatchDTO mapToDTO(Match match) {
        MatchDTO matchDTO = new MatchDTO();
        matchDTO.setId(match.getId());
        matchDTO.setLocation(match.getLocation());
        matchDTO.setDateTime(match.getDateTime());
        matchDTO.setMaxPlayers(match.getMaxPlayers());
        matchDTO.setCurrentPlayers(match.getCurrentPlayers());
        matchDTO.setOrganizerName(match.getOrganizer() != null ? match.getOrganizer().getName() : "No Organizer");

        matchDTO.setParticipantNames(match.getParticipants().stream()
                .map(participant -> participant.getUser().getName())
                .collect(Collectors.toList()));
        return matchDTO;
    }

}
