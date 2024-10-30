package com.cricinfo.sportsCentre.Entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "team_entry")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TeamsEntry {
    @Id
    private ObjectId id;
    @NonNull
    @Indexed(unique = true)
    private String userName;
    private String teamName;
    private Long totalMatches;
    private Long matchesWon;
    private String teamType;
    private String email;
    @NonNull
    private String password;
    @DBRef
    private List<PlayerEntry> playerEntries = new ArrayList<>();
    private List<String> roles;
}
