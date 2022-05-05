package io.wisoft.aoiandregionmatcherv1.service;

import io.wisoft.aoiandregionmatcherv1.dto.Point;
import io.wisoft.aoiandregionmatcherv1.dto.RegisterRegionRequest;
import io.wisoft.aoiandregionmatcherv1.repository.RegionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
class RegionServiceTest {

  @Autowired
  RegionRepository regionRepository;

  RegionService regionService;

  @BeforeEach
  void setup() {
    regionService = new RegionService(regionRepository);
  }

  @Test
  void saveRegionTest() {
    final String name = "행정구역";
    final List<Point> area = List.of(new Point(1, 2), new Point(3, 4), new Point(1, 2));
    final RegisterRegionRequest request = new RegisterRegionRequest(name, area);

    regionService.register(request);
  }

}