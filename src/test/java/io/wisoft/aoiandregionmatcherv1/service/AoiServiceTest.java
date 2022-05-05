package io.wisoft.aoiandregionmatcherv1.service;

import io.wisoft.aoiandregionmatcherv1.dto.Point;
import io.wisoft.aoiandregionmatcherv1.dto.RegisterAoiRequest;
import io.wisoft.aoiandregionmatcherv1.repository.AoiRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
class AoiServiceTest {

  @Autowired
  AoiRepository aoiRepository;

  AoiService aoiService;

  @BeforeEach
  void setup() {
    aoiService = new AoiService(aoiRepository);
  }

  @Test
  void saveAoiTest() {
    final String name = "관심구역";
    final List<Point> area = List.of(new Point(1, 2), new Point(3, 4), new Point(1, 2));
    final RegisterAoiRequest request = new RegisterAoiRequest(name, area);

    aoiService.register(request);
  }

}