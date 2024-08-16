package com.cricinfo.sportsCentre.Controller;

import com.cricinfo.sportsCentre.Entity.PlayerEntry;
import com.cricinfo.sportsCentre.Service.PlayerEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/player")
public class PlayersEntryControllerV2 {

    @Autowired
    PlayerEntryService playerEntryService;

    @GetMapping
    public ResponseEntity<List<PlayerEntry>> getAllPlayers() {
        List<PlayerEntry> playerEntries = playerEntryService.showPlayerEntries();
        if(playerEntries.isEmpty()) {
            return new ResponseEntity<>(playerEntries, HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(playerEntries,HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<PlayerEntry> createPlayer(@RequestBody PlayerEntry player) {
        try{
            playerEntryService.savePlayerEntry(player);
            return new ResponseEntity<>(player,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<PlayerEntry> getPlayerById(@PathVariable ObjectId myId) {
        Optional<PlayerEntry> playerById = playerEntryService.findPlayerById(myId);
        if(playerById.isPresent()){
            return new ResponseEntity<>(playerById.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deletePlayerById(@PathVariable ObjectId myId) {
        if(playerEntryService.findPlayerById(myId).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            
        }else{
            playerEntryService.deleteById(myId);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
    }

    @PutMapping("id/{myId}")
    public ResponseEntity<?> updateById(@PathVariable ObjectId myId, @RequestBody PlayerEntry player) {
        PlayerEntry old = playerEntryService.findPlayerById(myId).orElse(null);
        if(old != null){
            old.setPlayerName(player.getPlayerName() != null && !player.getPlayerName().isEmpty() ? player.getPlayerName() : old.getPlayerName());
            old.setPassword(player.getPassword() != null && !player.getPassword().isEmpty() ? player.getPassword() : old.getPassword());
            old.setMatches(player.getMatches() != null && !player.getMatches().isEmpty() ? player.getMatches() : old.getMatches());
            old.setRunsTotal(player.getRunsTotal() != null ? player.getRunsTotal() : old.getRunsTotal());
            playerEntryService.savePlayerEntry(old);
            return new ResponseEntity<>(player,HttpStatus.CREATED);
        }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

// added to git