package io.wisoft.aoiandregionmatcherv1.service;

import io.wisoft.aoiandregionmatcherv1.dto.FindAoiNearestFromPointRequest;
import io.wisoft.aoiandregionmatcherv1.dto.Point;
import io.wisoft.aoiandregionmatcherv1.dto.RegisterAoiRequest;
import io.wisoft.aoiandregionmatcherv1.entity.Aoi;
import io.wisoft.aoiandregionmatcherv1.repository.AoiRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
class AoiServiceTest {

  @Autowired
  AoiRepository aoiRepository;

  AoiService aoiService;
  Aoi expectedAoi;

  @BeforeEach
  void setup() {
    aoiRepository.deleteAll();

    final String name = "경복궁(테스트)";
    final List<Point> area = List.of(
        new Point(126.979, 37.576),
        new Point(126.979, 37.584),
        new Point(126.974, 37.584),
        new Point(126.974, 37.576),
        new Point(126.979, 37.576));

    expectedAoi = new Aoi(name, area);

    aoiRepository.save(expectedAoi);
    aoiService = new AoiService(aoiRepository);
  }

  @Test
  void saveAoiTest() {
    final String name = "관심구역";
    final List<Point> area = List.of(new Point(1, 2), new Point(3, 4), new Point(1, 2));
    final RegisterAoiRequest request = new RegisterAoiRequest(name, area);

    aoiService.register(request);
  }

  @Test
  void findAoiNearestFromPointTest() {
    final FindAoiNearestFromPointRequest request = new FindAoiNearestFromPointRequest(126.979, 37.576);

    final Aoi aoi = aoiService.findAoiNearestFromPoint(request);

    assertThat(aoi.getId()).isEqualTo(expectedAoi.getId());
    assertThat(aoi.getName()).isEqualTo(expectedAoi.getName());
    assertThat(aoi.getArea()).isEqualTo(expectedAoi.getArea());
  }

}