package io.wisoft.aoiandregionmatcherv1.repository;

import io.wisoft.aoiandregionmatcherv1.annotation.IntegrationTest;
import io.wisoft.aoiandregionmatcherv1.dto.Point;
import io.wisoft.aoiandregionmatcherv1.entity.Aoi.AoiRow;
import io.wisoft.aoiandregionmatcherv1.entity.Region;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@IntegrationTest
class RegionRepositoryTest {

  @Autowired
  RegionRepository regionRepository;

  @Test
  void saveRegionTest() {
    final String name = "행정지역";
    final List<Point> points = List.of(new Point(1, 2), new Point(2, 3), new Point(1, 2));

    final Region region = new Region(name, points);

    regionRepository.save(region);
  }

  @Test
  void findAoisIntersectRegionTest() {
    final int regionId = 1;
    final List<AoiRow> aoiRows = regionRepository.findAoisIntersectRegion(regionId);
    assertThat(aoiRows).isNotNull();
  }

}