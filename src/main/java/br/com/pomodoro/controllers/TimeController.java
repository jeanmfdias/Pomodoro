package br.com.pomodoro.controllers;

import br.com.pomodoro.models.Time;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeController {
    @PostMapping("/time")
    public Time newTime(@RequestBody Time newTime) {
        return newTime;
    }
}
