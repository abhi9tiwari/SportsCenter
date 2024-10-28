package com.cricinfo.sportsCentre.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "team_entry")
@Data
@NoArgsConstructor
public class TeamsEntry {
    @Id
    private ObjectId id;
    @NonNull
    @Indexed(unique = true)
    private String userName;
    @NonNull
    private String password;
    private String teamName;
    private String jersey;
    private int matchesWon;
    private int worldCups;
    private String teamType;
    @DBRef
    private List<PlayerEntry> players;
}
