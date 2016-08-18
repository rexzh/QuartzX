package com.quartzx.datacollector;

import com.quartzx.datacollector.model.RFIDData;
import com.quartzx.datacollector.model.Summarize;
import com.quartzx.datacollector.utility.JsonConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;


import java.time.ZonedDateTime;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by zling on 6/21/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class})
public class JsonTest {
    @Test
    public void testToJsonString() {
        Summarize s = new Summarize();
        s.setSum(100);

        String json = JsonConverter.toJsonString(s);
        assertEquals("{\"sum\":100}", json);
    }

    @Test
    public void testToObject() {
        String json ="{\"sum\":100}";

        Summarize s = JsonConverter.toObject(json, Summarize.class);
        assertEquals(100, s.getSum());
    }

    @Test
    public void testParseRFIDData(){
        String json = "{\"id\":\"12345\",\"timestamp\":\"2016-06-24T16:43:46.457Z\",\"tagId\":\"00310001000000000000003400C6000000000000000000000C123456789999555522221111\"}";
        RFIDData data = JsonConverter.toObject(json, RFIDData.class);
        assertEquals("12345", data.getId());
        assertEquals("00310001000000000000003400C6000000000000000000000C123456789999555522221111", data.getTagId());

        long ep = data.getDeviceTime();
        assertEquals(1466786626457L, ep);
    }

    @Test
    public void testParseRFIDDataArray(){
        String json = "[{\"id\":\"12345\",\"timestamp\":\"2016-06-24T16:43:46.457Z\",\"tagId\":\"00310001000000000000003400C6000000000000000000000C123456789999555522221111\"}]";
        RFIDData[] arr = JsonConverter.toObject(json, RFIDData[].class);
        assertEquals(1, arr.length);
        RFIDData data = arr[0];
        assertEquals("12345", data.getId());
        assertEquals("00310001000000000000003400C6000000000000000000000C123456789999555522221111", data.getTagId());

        long ep = data.getDeviceTime();
        assertEquals(1466786626457L, ep);
    }
}
