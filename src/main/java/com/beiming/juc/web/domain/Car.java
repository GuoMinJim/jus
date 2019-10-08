package com.beiming.juc.web.domain;

import javax.persistence.*;

public class Car {
    private Integer id;

    private String name;

    private String brand;



    public void ready() {
        System.out.println(this.getName() + "已经ready");
    }

    public void run() {
        System.out.println(this.getName() + "开始比赛");
    }

    public Car(Integer id) {
        this.id = id;
    }

    public Car(String name) {
        this.name = name;
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @param brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }
}