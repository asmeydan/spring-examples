package com.asmeydan.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private long id;

    String name;

    String surname;

    List<String> addresses;
}
