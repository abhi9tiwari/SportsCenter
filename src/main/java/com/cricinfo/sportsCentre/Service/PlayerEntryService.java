package com.cricinfo.sportsCentre.Service;

import com.cricinfo.sportsCentre.Entity.PlayerEntry;
import com.cricinfo.sportsCentre.Repository.PlayerEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PlayerEntryService {

    @Autowired
    PlayerEntryRepo playerEntryRepo;

    public List<PlayerEntry> showPlayerEntries(){
        return playerEntryRepo.findAll();
    }

    public void savePlayerEntry(PlayerEntry player){
        playerEntryRepo.save(player);
    }

    public Optional<PlayerEntry> findPlayerById(ObjectId id){
        return playerEntryRepo.findById(id);
    }

    public void deleteById(ObjectId id){
        playerEntryRepo.deleteById(id);
    }

}
