package com.BetfairBootcamp.FootballApp.dtos;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantDTO {
    private Long id;
    private String userName;
    private Long matchId;
}
