package com.cricinfo.sportsCentre.Repository;

import com.cricinfo.sportsCentre.Entity.PlayerEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface PlayerEntryRepo extends MongoRepository<PlayerEntry, ObjectId>{

}
