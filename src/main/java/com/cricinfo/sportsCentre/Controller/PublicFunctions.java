package com.cricinfo.sportsCentre.Controller;

import com.cricinfo.sportsCentre.Entity.TeamsEntry;
import com.cricinfo.sportsCentre.Service.TeamEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/public")
public class PublicFunctions {

    @Autowired
    TeamEntryService teamEntryService;

    @PostMapping("/createTeam")
    public TeamsEntry createTeam(@RequestBody TeamsEntry team){
        return teamEntryService.saveNewTeam(team);
    }
}
