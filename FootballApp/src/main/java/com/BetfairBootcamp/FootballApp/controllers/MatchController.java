package com.BetfairBootcamp.FootballApp.controllers;

import com.BetfairBootcamp.FootballApp.dtos.MatchDTO;
import com.BetfairBootcamp.FootballApp.dtos.ParticipantDTO;
import com.BetfairBootcamp.FootballApp.entities.Match;
import com.BetfairBootcamp.FootballApp.entities.User;
import com.BetfairBootcamp.FootballApp.repositories.UserRepository;
import com.BetfairBootcamp.FootballApp.services.MatchService;
import com.BetfairBootcamp.FootballApp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/matches")
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;
    private final UserRepository userRepository;

//    @PostMapping("/create")
//    public MatchDTO createMatch(@RequestBody Match match) {
//        return matchService.createMatch(match);
//    }

    // ---------------ASTA AR FI BUN CHIAR SI CEL DE SUS A MERS--------------------
//    @PostMapping("/create")
//    public String createMatch(@RequestBody Match match) {
//        if (match.getOrganizer() == null || match.getOrganizer().getId() == null) {
//            throw new RuntimeException("Organizer ID is missing.");
//        }
//
//        matchService.createMatchWithOrganizer(match, match.getOrganizer().getId());
//        return "redirect:/matches";
//    }

    @PostMapping("/create")
    public String createMatch(@RequestParam String location,
                              @RequestParam String dateTime,
                              @RequestParam int maxPlayers,
                              @RequestParam Long organizerId) {
        Match match = new Match();
        match.setLocation(location);
        match.setDateTime(dateTime);
        match.setMaxPlayers(maxPlayers);

        User organizer = userRepository.findById(organizerId)
                .orElseThrow(() -> new RuntimeException("Organizer not found with ID: " + organizerId));
        match.setOrganizer(organizer);

        matchService.createMatch(match);
        return "redirect:/matches/page";
    }

    @GetMapping
    public List<MatchDTO> getAllMatches() {
        return matchService.getAllMatches();
    }

    @GetMapping("/{id}")
    public MatchDTO getMatchById(@PathVariable Long id) {
        return matchService.getMatchById(id)
                .map(matchService::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Match not found with id: " + id));
    }

    @PutMapping("/{id}")
    public MatchDTO updateMatch(@PathVariable Long id, @RequestBody Match updatedMatch) {
        return matchService.updateMatch(id, updatedMatch);
    }

    @DeleteMapping("/{id}")
    public String deleteMatch(@PathVariable Long id) {
        matchService.deleteMatch(id);
        return "Match with id " + id + " has been deleted.";
    }
}

