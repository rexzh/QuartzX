package com.quartzx.datacollector.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by zling on 6/14/2016.
 */
@Entity
public class Test implements Serializable{
    Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}

    String name;
    @Column
    public String getName(){return name;}
    public void setName(String name){this.name = name;}

}
