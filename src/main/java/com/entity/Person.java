package com.entity;

import javax.persistence.*;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "this_is_a_name")
    private String thisIsAName;

    public Person() {
    }

    public Person(String thisIsAName) {
        this.thisIsAName = thisIsAName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getThisIsAName() {
        return thisIsAName;
    }

    public void setThisIsAName(String thisIsAName) {
        this.thisIsAName = thisIsAName;
    }
}
