package com.cricinfo.sportsCentre.Entity;

import lombok.Data;

@Data
public class TeamsEntry {
    private String teamName;
    private String jersey;
    private int matchesWon;
    private int worldCups;
}
