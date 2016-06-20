package com.quartzx.datacollector.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by zling on 6/17/2016.
 */
@Configuration
@PropertySource("classpath:app.properties")
public class AppProperty {
    @Value("${mongodb.name}")
    private String mongoDbName;
    public  String getMongoDbName(){
        return mongoDbName;
    }

    public void setMongoDbName(String mongoDbName) {
        this.mongoDbName = mongoDbName;
    }

    @Value("${mongodb.server.host}")
    private String mongoDbServerHost;
    public String getMongoDbServerHost() {
        return mongoDbServerHost;
    }

    public void getMongoDbServerHost(String mongoDbServerHost){
        this.mongoDbServerHost = mongoDbServerHost;
    }

    @Value("${mongodb.server.port}")
    private int mongoDbServerPort;
    public int getMongoDbServerPort(){
        return mongoDbServerPort;
    }

    public void setMongoDbServerPort(int mongoDbServerPort){
        this.mongoDbServerPort = mongoDbServerPort;
    }
}
