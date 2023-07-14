package com.asmeydan.controller;

import com.asmeydan.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.asmeydan.service.UserService;

import java.util.List;

@RestController
@RequestMapping ("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.save(userDto));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> readAll() {
        return ResponseEntity.ok(userService.getAll());
    }
}
