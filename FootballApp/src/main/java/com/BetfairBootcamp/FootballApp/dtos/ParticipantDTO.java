package com.BetfairBootcamp.FootballApp.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ParticipantDTO {
    private Long id;
    private Long userId;
    private Long matchId;
}
