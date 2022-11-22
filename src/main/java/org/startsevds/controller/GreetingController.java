package org.startsevds.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.startsevds.repository.UserJpaRepository;

@RestController
@RequestMapping("/greeting")
@Component
@RequiredArgsConstructor
public class GreetingController {
    private final UserJpaRepository repository;

    @GetMapping("/{name}")
    public @ResponseBody String userGreeting(@PathVariable String name) {
        return greet(name);
    }

    @GetMapping("/master")
    public @ResponseBody String masterGreeting() {
        return greet(repository.findByName("Van Darkholme").getName());
    }

    private String greet(String name) {
        return "Guten Tag Herr " + name + "!";
    }
}
