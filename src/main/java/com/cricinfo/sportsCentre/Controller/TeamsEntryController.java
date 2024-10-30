package com.cricinfo.sportsCentre.Controller;

import com.cricinfo.sportsCentre.Entity.TeamsEntry;
import com.cricinfo.sportsCentre.Repository.TeamEntryRepo;
import com.cricinfo.sportsCentre.Service.TeamEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamsEntryController {

    @Autowired
    TeamEntryService teamEntryService;

    @Autowired
    TeamEntryRepo teamEntryRepo;

    @GetMapping("/getAllTeams")
    public List<TeamsEntry> getAllTeams(){
        return teamEntryService.getAllTeams();
    }

    @DeleteMapping("deleteTeam")
    public ResponseEntity<?> deleteTeam() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        TeamsEntry team = teamEntryService.findByUserName(authentication.getName());
        if(team != null){
            teamEntryService.deleteUsingUserName(authentication.getName());
            return new ResponseEntity<>("Team deleted", HttpStatus.OK);
        }
        throw new Exception("No team exists");
    }

    @PutMapping("/updateTeam")
    public ResponseEntity<?> updateTeam(@RequestBody TeamsEntry team) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // add mandatory checks in controller for requestBody params != null
        TeamsEntry currentTeam = teamEntryService.findByUserName(authentication.getName());
        if(currentTeam != null){
            currentTeam.setUserName(team.getUserName());
            currentTeam.setPassword(team.getPassword());
            if(team.getTeamType() != null) currentTeam.setTeamName(team.getTeamName());
            if(team.getTotalMatches() != null)currentTeam.setTotalMatches(team.getTotalMatches());
            if(team.getMatchesWon() != null)currentTeam.setMatchesWon(team.getMatchesWon());
            if(team.getPlayerEntries() != null)currentTeam.setPlayerEntries(team.getPlayerEntries());
            if(team.getTeamType() != null)currentTeam.setTeamType(team.getTeamType());
            if(team.getEmail() != null)currentTeam.setEmail(team.getEmail());
            teamEntryService.saveTeam(currentTeam);
            return new ResponseEntity<>("User Updated", HttpStatus.OK);
        }
        throw new Exception("No player exists");
    }
}
