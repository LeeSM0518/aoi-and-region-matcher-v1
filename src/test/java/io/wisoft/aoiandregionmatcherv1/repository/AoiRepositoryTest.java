package io.wisoft.aoiandregionmatcherv1.repository;

import io.wisoft.aoiandregionmatcherv1.dto.Point;
import io.wisoft.aoiandregionmatcherv1.entity.Aoi;
import io.wisoft.aoiandregionmatcherv1.entity.Aoi.AoiRow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Polygon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static io.wisoft.aoiandregionmatcherv1.entity.factory.PolygonFactory.createPolygon;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
class AoiRepositoryTest {

  @Autowired
  AoiRepository aoiRepository;

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
  }

  @Test
  void saveAoiTest() {
    final String name = "관심지역";
    final List<Point> points = List.of(new Point(1, 2), new Point(3, 4), new Point(1, 2));

    final Aoi aoi = new Aoi(name, points);

    aoiRepository.save(aoi);
  }

  @Test
  void findAoiNearestFromPointTest() {
    final Point point = new Point(126.979, 37.576);
    final AoiRow aoi = aoiRepository.findAoiNearestFromPoint(point.getX(), point.getY());

    final Integer id = aoi.getId();
    final String name = aoi.getName();
    final Polygon area = createPolygon(aoi.getAreaText());

    assertThat(id).isEqualTo(expectedAoi.getId());
    assertThat(name).isEqualTo(expectedAoi.getName());
    assertThat(area).isEqualTo(expectedAoi.getArea());
  }

}