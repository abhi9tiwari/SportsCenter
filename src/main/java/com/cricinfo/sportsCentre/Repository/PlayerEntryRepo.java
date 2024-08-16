package com.cricinfo.sportsCentre.Repository;

import com.cricinfo.sportsCentre.Entity.PlayerEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerEntryRepo extends MongoRepository<PlayerEntry, ObjectId>{

}
