package com.quartzx.datacollector;

import com.quartzx.datacollector.model.RFIDData;
import com.quartzx.datacollector.utility.JsonConverter;
import com.quartzx.datacollector.utility.ResourceManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by zling on 6/27/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class})
public class ResourceManagerTest {
    @Test
    public void testReadResource(){
        ResourceManager mgr = new ResourceManager();
        String rfid = mgr.read("rfid.json");
        assertNotNull(rfid);
        RFIDData data = JsonConverter.toObject(rfid, RFIDData.class);
        assertEquals("12345", data.getId());
    }
}
