package com.BetfairBootcamp.FootballApp.controllers;

import com.BetfairBootcamp.FootballApp.dtos.ParticipantDTO;
import com.BetfairBootcamp.FootballApp.entities.Participant;
import com.BetfairBootcamp.FootballApp.services.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/participants")
@RequiredArgsConstructor
public class ParticipantController {

    private final ParticipantService participantService;

    @PostMapping("/add")
    public ParticipantDTO addParticipant(@RequestParam String userName, @RequestParam(required = false) Long newMatchId) {
        return participantService.addParticipant(userName, newMatchId);
    }

    @PutMapping("/{participantId}/assignToMatch")
    public ParticipantDTO assignPlayerToMatch(@PathVariable Long participantId, @RequestBody Map<String, Long> request) {
        Long matchId = request.get("matchId");
        return participantService.assignPlayerToMatch(participantId, matchId);
    }

    @GetMapping
    public List<ParticipantDTO> getAllParticipants() {
        return participantService.getAllParticipants();
    }

    @GetMapping("/{id}")
    public ParticipantDTO getParticipantById(@PathVariable Long id) {
        return participantService.getParticipantById(id);
    }

    @PutMapping("/{id}")
    public ParticipantDTO updateParticipant(@PathVariable Long id, @RequestBody ParticipantDTO participantDTO) {
        return participantService.updateParticipant(id, participantDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteParticipant(@PathVariable Long id) {
        participantService.deleteParticipant(id);
        return "Participant with ID " + id + " has been deleted.";
    }
}
