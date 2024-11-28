package com.BetfairBootcamp.FootballApp.controllers;

import com.BetfairBootcamp.FootballApp.dtos.ParticipantDTO;
import com.BetfairBootcamp.FootballApp.services.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/participants")
@RequiredArgsConstructor
public class ParticipantPageController {

    private final ParticipantService participantService;

    @GetMapping
    public String getParticipantsPage(Model model) {
        List<ParticipantDTO> participants = participantService.getAllParticipants();

        participants.forEach(participant -> {
            System.out.println("Participant ID: " + participant.getId());
            System.out.println("User Name: " + participant.getUserName());
            System.out.println("Match ID: " + participant.getMatchId());
        });

        model.addAttribute("participants", participants);
        return "participants";
    }

    @PostMapping("/assign")
    public String assignParticipantToMatch(@RequestParam Long participantId, @RequestParam Long matchId) {
        participantService.assignPlayerToMatch(participantId, matchId);
        return "redirect:/participants"; // Reload the page after assignment
    }
}

