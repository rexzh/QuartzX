package com.quartzx.datacollector;

import javax.inject.Inject;
import javax.inject.Named;

import com.quartzx.datacollector.dao.ISummaryDao;
import com.quartzx.datacollector.dao.SummaryDao;
import com.quartzx.datacollector.model.Summarize;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by zling on 5/23/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class})
public class DaoTest{
    //@Inject
    //ISummaryDao dao;

    @Test
    public void ReadTest() {
        ISummaryDao dao = new SummaryDao();
        Summarize s = dao.analysis();
        assertEquals(100, s.getAverage(), 0.001);
        assertEquals(300, s.getSum(), 0.001);
    }
}
