package ru.peter.mongo.common;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import ru.peter.domain.common.port.IdGenerator;

@Component
public class MongoIdGenerator implements IdGenerator {
    @Override
    public String generate() {
        return new ObjectId().toString();
    }

    public ObjectId parse(String idString){
        return new ObjectId(idString);
    }
}
