package com.geekbrains.spring.web.api.core;

public class OrderDetailsDto {
    private String index;
    private String city;
    private String street;
    private String house;
    private String phone;

    public OrderDetailsDto() {
    }

    public OrderDetailsDto(String index, String city, String street, String house, String phone) {
        this.index = index;
        this.city = city;
        this.street = street;
        this.house = house;
        this.phone = phone;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
