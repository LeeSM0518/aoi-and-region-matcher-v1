package io.wisoft.aoiandregionmatcherv1.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class RegisterRegionRequest {

  private final String name;
  private final List<Point> area;

}
