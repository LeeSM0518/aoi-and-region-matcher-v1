package io.wisoft.aoiandregionmatcherv1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FindAoisIncludedRegionResponse {

  private List<AoiDto> aois;

}
