package com.cricinfo.sportsCentre.Repository;

import com.cricinfo.sportsCentre.Entity.TeamsEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeamEntryRepo extends MongoRepository<TeamsEntry, ObjectId> {
    TeamsEntry findByUserName(String userName);
    TeamsEntry deleteByUserName(String userName);
}
