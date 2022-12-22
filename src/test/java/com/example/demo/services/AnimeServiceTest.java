package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.Test;

class AnimeServiceTest {

  @Test
  public void shouldParseRandomAnimeResponse() throws IOException {
    // Arrange
    // Mock external service responses
    MockWebServer server = new MockWebServer();
    final String mockResponseRaw = readResourceFile("/anime/response-ok.json");

    MockResponse mockResponse = new MockResponse()
        .setBody(mockResponseRaw)
        .addHeader("Content-Type", "application/json; charset=utf-8");

    server.enqueue(mockResponse);
    server.start();

    // Create necessary objects
    final OkHttpClient httpClient = new OkHttpClient();

    final ObjectMapper mapper = new ObjectMapper()
        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        .disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);

    final String baseUrl = server.url("/").toString();

    // Act
    final AnimeService animeService = new AnimeService(httpClient, mapper, baseUrl);
    final String actualUrl = animeService.getRandom().url();

    // Assert
    assertEquals("https://myanimelist.net/anime/35840/Hataraku_Saibou_CM", actualUrl);
  }

  private String readResourceFile(String fileName) throws IOException {

    final byte[] bytes = AnimeServiceTest.class.getResourceAsStream(fileName).readAllBytes();
    return new String(bytes);
  }
}