package com.quartzx.datacollector.utility;

/**
 * Created by zling on 6/20/2016.
 */
public enum MongoDBCollections {
    Data("data");

    public String getCode() {
        return code;
    }

    private final String code;

    MongoDBCollections(String code) {
        this.code = code;
    }

    public static MongoDBCollections fromCode(String code) {
        for (MongoDBCollections collection : MongoDBCollections.values()) {
            if (collection.code.equals(code))
                return collection;
        }
        return null;
    }
}
