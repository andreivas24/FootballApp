package com.BetfairBootcamp.FootballApp.controllers;

import com.BetfairBootcamp.FootballApp.dtos.ParticipantDTO;
import com.BetfairBootcamp.FootballApp.entities.Participant;
import com.BetfairBootcamp.FootballApp.services.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/participants")
@RequiredArgsConstructor
public class ParticipantController {

    private final ParticipantService participantService;

    @PostMapping("/add")
    public ParticipantDTO addParticipant(@RequestBody ParticipantDTO participantDTO) {
        return participantService.addParticipant(participantDTO);
    }

    @PutMapping("/{participantId}/assignMatch")
    public ParticipantDTO assignMatch(@PathVariable Long participantId, @RequestBody Map<String, Long> request) {
        Long matchId = request.get("matchId");
        return participantService.assignMatch(participantId, matchId);
    }

}
