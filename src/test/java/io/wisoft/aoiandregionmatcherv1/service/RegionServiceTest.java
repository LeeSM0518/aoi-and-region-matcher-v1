package io.wisoft.aoiandregionmatcherv1.service;

import io.wisoft.aoiandregionmatcherv1.annotation.IntegrationTest;
import io.wisoft.aoiandregionmatcherv1.dto.FindAoisIntersectRegionRequest;
import io.wisoft.aoiandregionmatcherv1.dto.Point;
import io.wisoft.aoiandregionmatcherv1.dto.RegisterRegionRequest;
import io.wisoft.aoiandregionmatcherv1.entity.Aoi;
import io.wisoft.aoiandregionmatcherv1.entity.Aoi.AoiRow;
import io.wisoft.aoiandregionmatcherv1.repository.RegionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@IntegrationTest
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

  @Test
  void findAoisIntersectRegionTest() {
    final int regionId = 1;
    final FindAoisIntersectRegionRequest request = new FindAoisIntersectRegionRequest(regionId);

    final List<AoiRow> aoiRows = List.of(new AoiRow() {
      @Override
      public Integer getId() {
        return 1;
      }

      @Override
      public String getName() {
        return "관심지역";
      }

      @Override
      public String getAreaText() {
        return "Polygon((1 1, 2 3, 3 4, 5 6, 1 1)";
      }
    });

    regionRepository = mock(RegionRepository.class);
    given(regionRepository.findAoisIntersectRegion(regionId)).willReturn(aoiRows);

    final List<Aoi> aoisIntersectRegion = regionService.findAoisIntersectRegion(request);

    assertThat(aoisIntersectRegion.get(0).getId()).isEqualTo(1);
  }

}