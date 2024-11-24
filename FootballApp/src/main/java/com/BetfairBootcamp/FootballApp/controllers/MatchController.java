package com.BetfairBootcamp.FootballApp.controllers;

import com.BetfairBootcamp.FootballApp.dtos.MatchDTO;
import com.BetfairBootcamp.FootballApp.entities.Match;
import com.BetfairBootcamp.FootballApp.services.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;

    @PostMapping("/create")
    public MatchDTO createMatch(@RequestBody Match match) {
        return matchService.createMatch(match);
    }

    @GetMapping
    public List<Match> getAllMatches() {
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

