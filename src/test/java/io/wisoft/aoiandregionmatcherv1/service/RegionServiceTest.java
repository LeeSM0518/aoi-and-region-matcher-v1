package io.wisoft.aoiandregionmatcherv1.service;

import io.wisoft.aoiandregionmatcherv1.dto.FindAoisIntersectRegionRequest;
import io.wisoft.aoiandregionmatcherv1.dto.Point;
import io.wisoft.aoiandregionmatcherv1.dto.RegisterRegionRequest;
import io.wisoft.aoiandregionmatcherv1.entity.Aoi;
import io.wisoft.aoiandregionmatcherv1.entity.Aoi.AoiRow;
import io.wisoft.aoiandregionmatcherv1.entity.Region;
import io.wisoft.aoiandregionmatcherv1.repository.RegionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.wisoft.aoiandregionmatcherv1.entity.factory.PolygonFactory.createPolygon;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class RegionServiceTest {

  RegionRepository regionRepository;

  RegionService regionService;

  @BeforeEach
  void setup() {
    regionRepository = mock(RegionRepository.class);
    regionService = new RegionService(regionRepository);
  }

  @Test
  void saveRegionTest() {
    final int expectedRegionId = 1;
    final String name = "행정구역";
    final List<Point> area = List.of(new Point(1, 2), new Point(3, 4), new Point(1, 2));
    final RegisterRegionRequest request = new RegisterRegionRequest(name, area);
    final Region mockRegion = mock(Region.class);

    given(mockRegion.getId()).willReturn(expectedRegionId);
    given(regionRepository.save(any())).willReturn(mockRegion);

    final Integer actualRegionId = regionService.register(request);

    assertThat(actualRegionId).isEqualTo(expectedRegionId);
  }

  @Test
  void findAoisIntersectRegionTest() {
    final int expectedAoiId = 1;
    final String expectedAoiName = "관심지역";
    final String expectedAoiArea = "Polygon((1 1, 2 3, 3 4, 5 6, 1 1))";
    final int regionId = 1;
    final FindAoisIntersectRegionRequest request = new FindAoisIntersectRegionRequest(regionId);

    final List<AoiRow> aoiRows = List.of(new AoiRow() {
      @Override
      public Integer getId() {
        return expectedAoiId;
      }

      @Override
      public String getName() {
        return expectedAoiName;
      }

      @Override
      public String getAreaText() {
        return expectedAoiArea;
      }
    });

    given(regionRepository.findAoisIntersectRegion(regionId)).willReturn(aoiRows);

    final List<Aoi> aoisIntersectRegion = regionService.findAoisIntersectRegion(request);

    assertThat(aoisIntersectRegion.get(0).getId()).isEqualTo(expectedAoiId);
    assertThat(aoisIntersectRegion.get(0).getName()).isEqualTo(expectedAoiName);
    assertThat(aoisIntersectRegion.get(0).getArea()).isEqualTo(createPolygon(expectedAoiArea));
  }

}