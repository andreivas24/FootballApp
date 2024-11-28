package com.BetfairBootcamp.FootballApp.controllers;

import com.BetfairBootcamp.FootballApp.dtos.MatchDTO;
import com.BetfairBootcamp.FootballApp.entities.Match;
import com.BetfairBootcamp.FootballApp.entities.User;
import com.BetfairBootcamp.FootballApp.repositories.UserRepository;
import com.BetfairBootcamp.FootballApp.services.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/matches")
@RequiredArgsConstructor
public class MatchPageController {

    private final MatchService matchService;
    private final UserRepository userRepository;

    @GetMapping("/page")
    public String getMatchesPage(Model model) {
        model.addAttribute("matches", matchService.getAllMatches());

        List<User> organizers = userRepository.findAllByRole("ORGANIZER");
        model.addAttribute("organizers", organizers);

        boolean isOrganizer = !organizers.isEmpty();
        model.addAttribute("isOrganizer", isOrganizer);

        return "matches";
    }

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
}


