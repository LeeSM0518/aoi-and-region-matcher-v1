package io.wisoft.aoiandregionmatcherv1.service;

import io.wisoft.aoiandregionmatcherv1.dto.FindAoiNearestFromPointRequest;
import io.wisoft.aoiandregionmatcherv1.dto.Point;
import io.wisoft.aoiandregionmatcherv1.dto.RegisterAoiRequest;
import io.wisoft.aoiandregionmatcherv1.entity.Aoi;
import io.wisoft.aoiandregionmatcherv1.repository.AoiRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class AoiServiceTest {

  AoiRepository aoiRepository;
  AoiService aoiService;

  @BeforeEach
  void setup() {
    aoiRepository = mock(AoiRepository.class);
    aoiService = new AoiService(aoiRepository);
  }

  @Test
  void saveAoiTest() {
    final int expectedAoiId = 1;
    final String name = "관심구역";
    final List<Point> area = List.of(new Point(1, 2), new Point(3, 4), new Point(1, 2));
    final RegisterAoiRequest request = new RegisterAoiRequest(name, area);
    final Aoi mockAoi = mock(Aoi.class);

    given(mockAoi.getId()).willReturn(expectedAoiId);
    given(aoiRepository.save(any())).willReturn(mockAoi);

    final Integer actualAoiId = aoiService.register(request);

    assertThat(actualAoiId).isEqualTo(expectedAoiId);
  }

  @Test
  void findAoiNearestFromPointTest() {
    final String expectedName = "관심지역";
    final List<Point> expectedArea = List.of(new Point(1, 2), new Point(3, 4), new Point(4, 5), new Point(1, 2));
    final Aoi expectedAoi = new Aoi(expectedName, expectedArea);

    final double latitude = 126.979;
    final double longitude = 37.576;
    final FindAoiNearestFromPointRequest request =
        new FindAoiNearestFromPointRequest(latitude, longitude);

    given(aoiRepository.findAoiNearestFromPoint(latitude, longitude)).willReturn(expectedAoi);

    final Aoi actualAoi = aoiService.findAoiNearestFromPoint(request);

    assertThat(actualAoi.getId()).isEqualTo(expectedAoi.getId());
    assertThat(actualAoi.getName()).isEqualTo(expectedAoi.getName());
    assertThat(actualAoi.getArea()).isEqualTo(expectedAoi.getArea());
  }

}