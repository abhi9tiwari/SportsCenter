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
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMatches() {
		return matches;
	}
	public void setMatches(String matches) {
		this.matches = matches;
	}
	public Integer getRunsTotal() {
		return runsTotal;
	}
	public void setRunsTotal(Integer runsTotal) {
		this.runsTotal = runsTotal;
	}
	public Integer getWicketsTotal() {
		return wicketsTotal;
	}
	public void setWicketsTotal(Integer wicketsTotal) {
		this.wicketsTotal = wicketsTotal;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
    
    
}
