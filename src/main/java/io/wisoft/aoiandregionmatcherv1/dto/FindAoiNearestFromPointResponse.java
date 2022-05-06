package io.wisoft.aoiandregionmatcherv1.dto;

import io.wisoft.aoiandregionmatcherv1.entity.Aoi;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FindAoiNearestFromPointResponse {

  private AoiDto aoi;

  public FindAoiNearestFromPointResponse(final Aoi aoi) {
    this.aoi = AoiDto.fromEntity(aoi);
  }

}
