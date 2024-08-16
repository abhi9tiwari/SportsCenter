package com.cricinfo.sportsCentre.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Data
@NoArgsConstructor
@Document(collection = "player_entries")
public class PlayerEntry {

    @Id
    private ObjectId id;
    private String playerName;
    private String password;
    private String matches;
    private Integer runsTotal;
    private Integer wicketsTotal;
    private LocalDateTime date;
}
