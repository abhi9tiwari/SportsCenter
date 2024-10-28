package com.cricinfo.sportsCentre.Controller;

import com.cricinfo.sportsCentre.Entity.TeamsEntry;
import com.cricinfo.sportsCentre.Service.TeamEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/team")
public class TeamsEntryController {

    @Autowired
    TeamEntryService teamEntryService;

    @GetMapping("/getAllTeams")
    public List<TeamsEntry> getAllTeams(){
        return teamEntryService.getAllTeams();
    }

    @PostMapping("/createTeam")
    public TeamsEntry createTeam(@RequestBody TeamsEntry team){
        return teamEntryService.saveTeam(team);
    }

    @PostMapping("deleteTeamById/{id}")
    public void deleteTeamById(@PathVariable ObjectId id){
        Optional<TeamsEntry> team = teamEntryService.findById(id);
        if(team.isPresent()){
            teamEntryService.deleteById(id);
        }
    }

    @PutMapping("/updateTeam/{userName}")
    public void updateTeam(@RequestBody TeamsEntry team, @PathVariable String userName){
        // add mandatory checks in controller for requestBody params != null
        TeamsEntry currentTeam = teamEntryService.findByUserName(userName);
        if(currentTeam != null){
            currentTeam.setUserName(team.getUserName());
            currentTeam.setPassword(team.getPassword());
            teamEntryService.saveTeam(currentTeam);
        }
    }
}
