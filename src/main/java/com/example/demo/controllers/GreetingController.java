package com.example.demo.controllers;

import com.example.demo.models.Greeting;
import com.example.demo.services.AnimeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

  @GetMapping("/hello")
  public String helloWorld() {
    return "Hello, world!";
  }

  @GetMapping("/greeting")
  public Greeting greeting(@RequestParam(value = "name", defaultValue = "unknown") String name) {
    return new Greeting(name);
  }
}
