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

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;
    private final UserRepository userRepository;

    public MatchDTO createMatch(Match match) {
        // Validate organizer
        User organizer = userRepository.findById(match.getOrganizer().getId())
                .orElseThrow(() -> new RuntimeException("Organizer not found with ID: " + match.getOrganizer().getId()));

        // Set the organizer
        match.setOrganizer(organizer);

        // Save the match
        Match savedMatch = matchRepository.save(match);

        return mapToDTO(savedMatch);
    }

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    public Optional<Match> getMatchById(Long id) {
        return matchRepository.findById(id);
    }

    public MatchDTO mapToDTO(Match match) {
        MatchDTO matchDTO = new MatchDTO();
        matchDTO.setId(match.getId());
        matchDTO.setLocation(match.getLocation());
        matchDTO.setDateTime(match.getDateTime());
        matchDTO.setMaxPlayers(match.getMaxPlayers());
        matchDTO.setCurrentPlayers(match.getCurrentPlayers());
        matchDTO.setOrganizerId(match.getOrganizer().getId());
        return matchDTO;
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
        if (matchRepository.existsById(id)) {
            matchRepository.deleteById(id);
        } else {
            throw new RuntimeException("Match not found with id: " + id);
        }
    }
}
