package io.wisoft.aoiandregionmatcherv1.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class RegisterRegionRequest {

  @NotNull
  @NotBlank
  private final String name;

  @NotNull
  @NotEmpty
  private final List<Point> area;

}
