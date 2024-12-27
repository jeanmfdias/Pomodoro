package br.com.pomodoro.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class IndexController {
    @GetMapping("/")
    public Map<String, String> getIndex() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Pomodoro");
        response.put("version", "2.0");
        return response;
    }
}
