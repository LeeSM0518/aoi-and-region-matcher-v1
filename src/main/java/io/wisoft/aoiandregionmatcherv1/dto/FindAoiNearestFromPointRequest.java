package io.wisoft.aoiandregionmatcherv1.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FindAoiNearestFromPointRequest {

  private final double latitude;
  private final double longitude;

}
