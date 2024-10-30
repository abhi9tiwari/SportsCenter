package com.cricinfo.sportsCentre.Controller;

import com.cricinfo.sportsCentre.Entity.PlayerEntry;
import com.cricinfo.sportsCentre.Entity.TeamsEntry;
import com.cricinfo.sportsCentre.Service.PlayerEntryService;
import com.cricinfo.sportsCentre.Service.TeamEntryService;
import lombok.SneakyThrows;
import org.apache.coyote.BadRequestException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/player")
public class PlayersEntryControllerV2 {

    @Autowired
    PlayerEntryService playerEntryService;

    @Autowired
    TeamEntryService teamEntryService;

    @GetMapping("/getAllPlayers")
    public ResponseEntity<List<PlayerEntry>> getAllPlayers() throws Exception {
        try{
            List<PlayerEntry> playerEntries = playerEntryService.showPlayerEntries();
            if(playerEntries.isEmpty()) {
                return new ResponseEntity<>(playerEntries, HttpStatus.NO_CONTENT);
            }else{
                return new ResponseEntity<>(playerEntries,HttpStatus.OK);
            }
        }catch(Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    @GetMapping("/getPlayersByTeam")
    public ResponseEntity<List<PlayerEntry>> getPlayersByTeam() throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        TeamsEntry team = teamEntryService.findByUserName(authentication.getName());
        List<PlayerEntry> players = team.getPlayerEntries();
        if(players != null && !players.isEmpty()) {
            return new ResponseEntity<>(team.getPlayerEntries(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/createPlayer")
    public ResponseEntity<PlayerEntry> createPlayer(@Validated @RequestBody PlayerEntry player) {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            playerEntryService.savePlayerEntry(player,authentication.getName());
            return new ResponseEntity<>(player,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @SneakyThrows
    @GetMapping("/getPlayerById/{myId}")
    public ResponseEntity<?> getPlayerById(@PathVariable ObjectId myId) {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            TeamsEntry team = teamEntryService.findByUserName(userName);
            List<PlayerEntry> list = team.getPlayerEntries().stream().filter(x -> x.getId().equals(myId)).toList();
            if(!list.isEmpty()){
                Optional<PlayerEntry> playerById = playerEntryService.findPlayerById(myId);
                if(playerById.isPresent()){
                    return new ResponseEntity<>(playerById.get(), HttpStatus.OK);
                }else{
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }else{
                return new ResponseEntity<>("Enter valid id",HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteById/{myId}")
    public ResponseEntity<?> deletePlayerById(ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        TeamsEntry team = teamEntryService.findByUserName(userName);
        List<PlayerEntry> list = team.getPlayerEntries().stream().filter(x -> x.getId().equals(myId)).toList();
        if(!list.isEmpty()){
            if(playerEntryService.findPlayerById(myId).isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else{
                playerEntryService.deleteById(myId,userName);
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            }
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Enter correct Id");
        }
    }

    @PutMapping("/updateById/{myId}")
    public ResponseEntity<?> updateById(@PathVariable ObjectId myId, @RequestBody PlayerEntry player) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        TeamsEntry team = teamEntryService.findByUserName(userName);
        List<PlayerEntry> list = team.getPlayerEntries().stream().filter(x -> x.getId().equals(myId)).toList();
        if(!list.isEmpty()) {
            PlayerEntry old = playerEntryService.findPlayerById(myId).orElse(null);
            if (old != null) {
                old.setRegisteredNumber(player.getRegisteredNumber() != null ? player.getRegisteredNumber() : old.getRegisteredNumber());
                old.setPlayerName(player.getPlayerName() != null && !player.getPlayerName().isEmpty() ? player.getPlayerName() : old.getPlayerName());
                old.setPassword(player.getPassword() != null && !player.getPassword().isEmpty() ? player.getPassword() : old.getPassword());
                old.setMatches(player.getMatches() != null && !player.getMatches().isEmpty() ? player.getMatches() : old.getMatches());
                old.setRunsTotal(player.getRunsTotal() != null ? player.getRunsTotal() : old.getRunsTotal());
                playerEntryService.savePlayerEntry(old);
                return new ResponseEntity<>(player, HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}