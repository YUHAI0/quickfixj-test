package com.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
public class DemoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "string_1")
    private String string1;

    @Column(name = "string_2")
    private String string2;

    @Column(name = "double_1")
    private Double double1;

    @Column(name = "int_1")
    private Integer int1;

    @Column(name = "int_2")
    private Integer int2;

    @JoinColumn(name = "person_id")
    @ManyToOne(cascade = CascadeType.ALL) // Mandatory Annotation
    private Person person;

//    public DemoEntity() { }

    public DemoEntity(String string1, String string2, Double double1, Integer int1, Integer int2, Person person) {
        this.id = id;
        this.string1 = string1;
        this.string2 = string2;
        this.double1 = double1;
        this.int1 = int1;
        this.int2 = int2;
        this.person = person;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getString1() {
        return string1;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public String getString2() {
        return string2;
    }

    public void setString2(String string2) {
        this.string2 = string2;
    }

    public Double getDouble1() {
        return double1;
    }

    public void setDouble1(Double double1) {
        this.double1 = double1;
    }

    public Integer getInt1() {
        return int1;
    }

    public void setInt1(Integer int1) {
        this.int1 = int1;
    }

    public Integer getInt2() {
        return int2;
    }

    public void setInt2(Integer int2) {
        this.int2 = int2;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
