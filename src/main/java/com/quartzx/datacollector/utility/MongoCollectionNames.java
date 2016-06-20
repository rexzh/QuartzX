package com.quartzx.datacollector.utility;

/**
 * Created by zling on 6/20/2016.
 */
public enum MongoCollectionNames {
    Data("data");

    public String getCode() {
        return code;
    }

    private final String code;

    MongoCollectionNames(String code) {
        this.code = code;
    }

    public static MongoCollectionNames fromCode(String code) {
        for (MongoCollectionNames collection : MongoCollectionNames.values()) {
            if (collection.code.equals(code))
                return collection;
        }
        return null;
    }
}
