package com.cricinfo.sportsCentre.Service;

import com.cricinfo.sportsCentre.Entity.TeamsEntry;
import com.cricinfo.sportsCentre.Repository.TeamEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TeamEntryService {

    @Autowired
    TeamEntryRepo teamEntryRepo;

    public TeamsEntry saveTeam(TeamsEntry team){
        teamEntryRepo.save(team);
        return team;
    }

    public List<TeamsEntry> getAllTeams(){
        return teamEntryRepo.findAll();
    }

    public Optional<TeamsEntry> findById(ObjectId id){
        return teamEntryRepo.findById(id);
    }

    public void deleteById(ObjectId id){
        teamEntryRepo.deleteById(id);
    }

    public TeamsEntry findByUserName(String username){
        return teamEntryRepo.findByUserName(username);
    }
}
