package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.Test;

public class AnimeServiceIT {

  // This test will make HTTP requests to upstream, making the test but INCONSISTENT and SLOW
  // Should only be used when absolutely necessary
  @Test
  public void shouldGetNarutoInfo() throws IOException {
    // Arrange
    final OkHttpClient httpClient = new OkHttpClient();

    final ObjectMapper mapper = new ObjectMapper()
        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        .disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);

    final String baseUrl = "https://api.jikan.moe/v4/";

    final AnimeService animeService = new AnimeService(httpClient, mapper, baseUrl);

    final long narutoId = 20L;
    final String narutoUrl = "https://myanimelist.net/anime/20/Naruto";

    // Act
    final String actualUrl = animeService.getById(narutoId).url();

    // Assert
    assertEquals(narutoUrl, actualUrl);
  }

}
