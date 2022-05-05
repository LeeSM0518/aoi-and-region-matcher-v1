package io.wisoft.aoiandregionmatcherv1.repository;

import io.wisoft.aoiandregionmatcherv1.dto.Point;
import io.wisoft.aoiandregionmatcherv1.entity.Aoi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
class AoiRepositoryTest {

  @Autowired
  AoiRepository aoiRepository;

  @Test
  void saveAoiTest() {
    final String name = "관심지역";
    final List<Point> points = List.of(new Point(1, 2), new Point(3, 4), new Point(1, 2));

    final Aoi aoi = new Aoi(name, points);

    aoiRepository.save(aoi);
  }

}