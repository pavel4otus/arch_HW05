package ru.pavel2107.arch.hzcatalog.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class User {
    private Long id;
    private String email;
    private String password;
    private String fio;
    private String phone;
    private Boolean active;
    private String role;
    private String address;
}
