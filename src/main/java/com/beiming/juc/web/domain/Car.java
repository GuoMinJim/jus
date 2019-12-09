package com.beiming.juc.web.domain;

public class Car {

  private Integer id;

  private String name;

  private String brand;

  public Car(Integer id, String name, String brand) {
    this.id = id;
    this.name = name;
    this.brand = brand;
  }

  public Car(Integer id) {
    this.id = id;
  }

  public Car(String name) {
    this.name = name;
  }

  public void ready() {
    System.out.println(this.getName() + "已经ready");
  }

  public void run() {
    System.out.println(this.getName() + "开始比赛");
  }

  /**
   * @return id
   */
  public Integer getId() {
    return id;
  }

  /**
   *
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
   *
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
   *
   */
  public void setBrand(String brand) {
    this.brand = brand;
  }
}