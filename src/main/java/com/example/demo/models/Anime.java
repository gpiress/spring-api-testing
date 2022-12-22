package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Anime(@JsonProperty("mal_id") long id, String url, int episodes, boolean airing) {

}
