package com.geekbrains.spring.web.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegRequest {
    private String login;
    private String password;
    private String email;

}
