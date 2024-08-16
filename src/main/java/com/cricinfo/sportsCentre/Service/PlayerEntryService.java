package com.cricinfo.sportsCentre.Service;

import com.cricinfo.sportsCentre.Entity.PlayerEntry;
import com.cricinfo.sportsCentre.Repository.PlayerEntryRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class PlayerEntryService {

    @Autowired
    PlayerEntryRepo playerEntryRepo;

    public List<PlayerEntry> showPlayerEntries(){
        return playerEntryRepo.findAll();
    }

    public void savePlayerEntry(PlayerEntry player){
        try {
            LocalDateTime lt = LocalDateTime.now();
            player.setDate(lt);
            playerEntryRepo.save(player);
        }catch(Exception e){
            log.error("Exception : ", e);
        }

    }

    public Optional<PlayerEntry> findPlayerById(ObjectId id){
        return playerEntryRepo.findById(id);
    }

    public void deleteById(ObjectId id){
        playerEntryRepo.deleteById(id);
    }

}
