package com.BetfairBootcamp.FootballApp.dtos;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchDTO {
    private Long id;
    private String location;
    private String dateTime;
    private int maxPlayers;
    private int currentPlayers;
    private String organizerName;
    private List<String> participantNames;
}
