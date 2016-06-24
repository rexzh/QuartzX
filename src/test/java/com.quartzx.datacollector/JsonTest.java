package com.quartzx.datacollector;

import com.quartzx.datacollector.model.Summarize;
import com.quartzx.datacollector.utility.JsonConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

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
}
