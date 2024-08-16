//package com.cricinfo.sportsCentre.Controller;
//
//import com.cricinfo.sportsCentre.Entity.PlayerEntry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/player")
//public class PlayersEntryController {
//
//    public Map<Long , PlayerEntry> playerEntries = new HashMap<>();
//
//    @GetMapping
//    public List<PlayerEntry> getAllPlayers(){
//        return new ArrayList<>(playerEntries.values());
//    }
//
//    @GetMapping("id/{myId}")
//    public PlayerEntry getPlayerById(@PathVariable Long myId){
//        return playerEntries.get(myId);
//    }
//
//    @DeleteMapping("id/{myId}")
//    public void deletePlayerById(@PathVariable Long myId){
//        playerEntries.remove(myId);
//    }
//
//    @PostMapping
//    public boolean createPlayer(@RequestBody PlayerEntry player){
//        playerEntries.put(player.getId(),player);
//        return true;
//    }
//
//    @PutMapping("id/{myId}")
//    public void updateById(@PathVariable Long myId, @RequestBody PlayerEntry player){
//        playerEntries.put(myId, player);
//    }
//}
