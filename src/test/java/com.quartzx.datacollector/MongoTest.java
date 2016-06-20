package com.quartzx.datacollector;

import com.mongodb.client.MongoDatabase;
import com.quartzx.datacollector.utility.MongoDBManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

/**
 * Created by zling on 6/20/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class})
public class MongoTest {
    @Inject
    private MongoDBManager mongoMgr;

    @Test
    public void testConnectAndFindSystemTable(){
        MongoDatabase db = mongoMgr.getDatabase();
        String systemIndex = "system.indexes";
        boolean found = false;
        for (String s : db.listCollectionNames()){
            if(s.equals(systemIndex)) {
                found = true;
                break;
            }
        }
        assertEquals(true, found);
    }
}
