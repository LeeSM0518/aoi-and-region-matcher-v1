package io.wisoft.aoiandregionmatcherv1.controller;

import io.wisoft.aoiandregionmatcherv1.dto.Point;
import io.wisoft.aoiandregionmatcherv1.dto.RegisterAoiRequest;
import io.wisoft.aoiandregionmatcherv1.dto.RegisterAoiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class AoiControllerTest {

  @Autowired
  private WebTestClient webTestClient;

  @Test
  void registerAoiTest() {
    final String name = "관심구역";
    final List<Point> area = List.of(new Point(1, 2), new Point(3, 4), new Point(1, 2));
    final RegisterAoiRequest request = new RegisterAoiRequest(name, area);

    this.webTestClient
        .post()
        .uri("/aois")
        .accept(APPLICATION_JSON)
        .bodyValue(request)
        .exchange()
        .expectStatus().is2xxSuccessful()
        .expectBody(new ParameterizedTypeReference<RegisterAoiResponse>() {
        })
        .consumeWith(response -> {
          final RegisterAoiResponse responseBody = response.getResponseBody();
          assertThat(responseBody).isNotNull();
        });
  }

}