package com.example.demo.services;

import com.example.demo.models.Anime;
import com.example.demo.models.AnimeResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AnimeService {

  private final OkHttpClient httpClient;
  private final ObjectMapper objectMapper;
  @Qualifier("anime-service-base-url")
  private final String baseUrl;

  public AnimeService(final OkHttpClient okHttpClient, final ObjectMapper objectMapper, final String baseUrl) {
    this.httpClient = okHttpClient;
    this.objectMapper = objectMapper;
    this.baseUrl = baseUrl.endsWith("/") ? baseUrl : String.format("%s/", baseUrl);
  }
  public Anime getRandom() {
    Request request = new Request.Builder()
        .url(baseUrl + "random/anime")
        .build();

    try (Response response = httpClient.newCall(request).execute()) {
      String rawContent = response.body().string();
      final AnimeResponse animeResponse = objectMapper.readValue(rawContent, AnimeResponse.class);

      return animeResponse.data;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Anime getById(final long id) {
    Request request = new Request.Builder()
        .url(String.format("%sanime/%d", baseUrl, id))
        .build();

    try (Response response = httpClient.newCall(request).execute()) {
      String rawContent = response.body().string();
      final AnimeResponse animeResponse = objectMapper.readValue(rawContent, AnimeResponse.class);

      return animeResponse.data;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
