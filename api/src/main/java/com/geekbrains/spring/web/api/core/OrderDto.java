package com.geekbrains.spring.web.api.core;

import java.math.BigDecimal;
import java.util.List;

public class OrderDto {
    private Long id;
    private String username;
    private List<OrderItemDto> items;
    private BigDecimal totalPrice;
    private String address;
    private String phone;
    private String status;

    public String getStringStatus() {
        return status;
    }

    public OrderStatus getStatus() {
        if(status.equals("CREATED")) return OrderStatus.CREATED;
        else if(status.equals("PAID")) return OrderStatus.PAID;
        else return OrderStatus.CANCELLED;
    }

    public void setStatus(OrderStatus status) {
        this.status = status.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDto> items) {
        this.items = items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public OrderDto() {
    }

    public OrderDto(Long id, String username, List<OrderItemDto> items,
                    BigDecimal totalPrice, String address, String phone, OrderStatus status) {
        this.id = id;
        this.username = username;
        this.items = items;
        this.totalPrice = totalPrice;
        this.address = address;
        this.phone = phone;
        this.status = status.toString();
        System.out.println(status.toString());;
    }
}
