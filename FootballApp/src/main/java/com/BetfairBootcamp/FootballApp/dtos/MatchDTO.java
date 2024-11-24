package com.BetfairBootcamp.FootballApp.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MatchDTO {
    private Long id;
    private String location;
    private String dateTime;
    private int maxPlayers;
    private int currentPlayers;
    private Long organizerId;
}
