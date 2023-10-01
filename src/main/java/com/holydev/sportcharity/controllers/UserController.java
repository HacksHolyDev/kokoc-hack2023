package com.holydev.sportcharity.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    //    Взаимодействие с профилем
    @GetMapping("profile")
    public ResponseEntity<String> getProfile() {
        return null;
    }

    //    Взаимодействие с курсами
    @PostMapping("attend")
    public ResponseEntity<String> attendCourse() {
        return null;
    }

    //    Взаимодействие с календарем
    @GetMapping("calendar")
    public ResponseEntity<String> getCalendar() {
        return null;
    }



}
