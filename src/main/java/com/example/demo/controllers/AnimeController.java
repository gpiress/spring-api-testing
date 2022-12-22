package com.example.demo.controllers;

import com.example.demo.models.Anime;
import com.example.demo.services.AnimeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("anime")
public class AnimeController {

  private final AnimeService animeService;

  AnimeController(AnimeService animeService) {
    this.animeService = animeService;
  }

  @GetMapping("/random")
  public Anime randomAnime() {
    return animeService.getRandom();
  }

  @GetMapping("/{id}")
  public Anime byId(@PathVariable String id) {
    return animeService.getById(Long.parseLong(id));
  }
}
