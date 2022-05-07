package io.wisoft.aoiandregionmatcherv1.controller;

import io.wisoft.aoiandregionmatcherv1.annotation.IntegrationTest;
import io.wisoft.aoiandregionmatcherv1.dto.FindAoisIntersectRegionResponse;
import io.wisoft.aoiandregionmatcherv1.dto.Point;
import io.wisoft.aoiandregionmatcherv1.dto.RegisterRegionRequest;
import io.wisoft.aoiandregionmatcherv1.dto.RegisterRegionResponse;
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
@IntegrationTest
class RegionControllerTest {

  @Autowired
  private WebTestClient webTestClient;

  @Test
  void registerRegionTest() {
    final String name = "행정구역";
    final List<Point> area = List.of(new Point(1, 2), new Point(3, 4), new Point(1, 2));
    final RegisterRegionRequest request = new RegisterRegionRequest(name, area);

    this.webTestClient
        .post()
        .uri("/regions")
        .accept(APPLICATION_JSON)
        .bodyValue(request)
        .exchange()
        .expectStatus().is2xxSuccessful()
        .expectBody(new ParameterizedTypeReference<RegisterRegionResponse>() {
        })
        .consumeWith(response -> {
          final RegisterRegionResponse responseBody = response.getResponseBody();
          assertThat(responseBody).isNotNull();
        });
  }

  @Test
  void findAoisIntersectRegionTest() {
    final int regionId = 1;
    this.webTestClient
        .get()
        .uri("/regions/" + regionId + "/aois/intersects")
        .exchange()
        .expectStatus().isOk()
        .expectHeader().valueEquals("Content-Type", "application/json")
        .expectBody(new ParameterizedTypeReference<FindAoisIntersectRegionResponse>() {
        })
        .consumeWith(response -> {
          final FindAoisIntersectRegionResponse responseBody = response.getResponseBody();
          assertThat(responseBody).isNotNull();
          assertThat(responseBody.getAois()).isNotNull();
        });

  }

}