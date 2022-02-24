package com.geekbrains.spring.web.api.core;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Модель элемента заказа")
public class OrderDetailsDto {

    @Schema(description = "Адрес", required = true, example = "190000, Россия, Санкт-Петербург, Невский пр-кт, д.1")
    private String address;

    @Schema(description = "Телефон", required = true, example = "+79000000000")
    private String phone;

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

    public OrderDetailsDto() {
    }

    public OrderDetailsDto(String address, String phone) {
        this.address = address;
        this.phone = phone;
    }
}
