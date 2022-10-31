package org.startsevds.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.startsevds.repository.UserJpaRepository;

@RestController
@RequestMapping("/greeting")
@Component
@RequiredArgsConstructor
public class GreetingController {
    private final UserJpaRepository repository;

    @GetMapping("/{name}")
    public ResponseEntity<String> userGreeting(@PathVariable String name) {
        return greet(name);
    }

    @GetMapping("/master")
    public ResponseEntity<String> masterGreeting() {
        return greet(repository.findByName("Van Darkholme").getName());
    }

    private ResponseEntity<String> greet(String name) {
        return ResponseEntity.ok("Guten Tag Herr " + name + "!");
    }
}
