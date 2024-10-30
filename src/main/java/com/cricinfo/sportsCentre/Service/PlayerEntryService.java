package com.cricinfo.sportsCentre.Service;

import com.cricinfo.sportsCentre.Entity.PlayerEntry;
import com.cricinfo.sportsCentre.Entity.TeamsEntry;
import com.cricinfo.sportsCentre.Repository.PlayerEntryRepo;

import lombok.SneakyThrows;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@Log
public class PlayerEntryService {

    @Autowired
    PlayerEntryRepo playerEntryRepo;

    @Autowired
    TeamEntryService teamEntryService;

    public List<PlayerEntry> showPlayerEntries(){
        return playerEntryRepo.findAll();
    }

    @SneakyThrows
    public void savePlayerEntry(PlayerEntry player, String userName){
        try {
            LocalDateTime lt = LocalDateTime.now();
            TeamsEntry team = teamEntryService.findByUserName(userName);
            player.setDate(lt);
            PlayerEntry savedPlayer = playerEntryRepo.save(player);
            team.getPlayerEntries().add(savedPlayer);
            teamEntryService.saveUser(team);
        }catch(Exception e){
            throw new Exception("an error has occured while saving ",e);
        }
    }

    public void savePlayerEntry(PlayerEntry player){
        playerEntryRepo.save(player);
    }

    public Optional<PlayerEntry> findPlayerById(ObjectId id){
        return playerEntryRepo.findById(id);
    }

    @Transactional
    public void deleteById(ObjectId id, String userName){
        TeamsEntry team = teamEntryService.findByUserName(userName);
        List<PlayerEntry> playerEntries = team.getPlayerEntries();
        playerEntries.removeIf(x -> x.getId() == id);
        team.setPlayerEntries(playerEntries);
        teamEntryService.saveUser(team);
        playerEntryRepo.deleteById(id);
    }

}
