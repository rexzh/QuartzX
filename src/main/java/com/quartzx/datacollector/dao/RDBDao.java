package com.quartzx.datacollector.dao;

import com.quartzx.datacollector.entity.Test;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Created by zling on 6/14/2016.
 */
public class RDBDao {

    private SessionFactory sf;
    public List<Test> findTest(){
        sf = new Configuration().configure().buildSessionFactory();
        Session s = sf.openSession();
        List<Test> t = s.createQuery("from Test").list();
        s.close();
        return t;
    }
}
