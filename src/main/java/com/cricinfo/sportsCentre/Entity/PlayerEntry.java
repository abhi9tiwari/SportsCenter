package com.cricinfo.sportsCentre.Entity;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "player_entries")
public class PlayerEntry {

    @Id
    private ObjectId id;
    @NotNull
    private Long registeredNumber;
    @NotNull
    private String playerName;
    private String password;
    private String matches;
    private Integer runsTotal;
    private Integer wicketsTotal;
    private LocalDateTime date;
    private List<Long> teamId;
}
