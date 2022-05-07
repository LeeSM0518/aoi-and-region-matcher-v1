package io.wisoft.aoiandregionmatcherv1.dto;

import io.wisoft.aoiandregionmatcherv1.entity.Aoi;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Getter
@NoArgsConstructor(access = PRIVATE)
public class FindAoiNearestFromPointResponse {

  private AoiDto aoi;

  public FindAoiNearestFromPointResponse(final Aoi aoi) {
    this.aoi = AoiDto.fromEntity(aoi);
  }

}
