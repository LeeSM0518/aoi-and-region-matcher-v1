package io.wisoft.aoiandregionmatcherv1.dto;

import io.wisoft.aoiandregionmatcherv1.entity.Aoi;
import lombok.Value;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class AoiDto {

  Integer id;
  String name;
  List<Point> area;

  public static AoiDto fromEntity(Aoi aoi) {
    final List<Point> area = Arrays
        .stream(aoi.getArea().getCoordinates())
        .map(coordinate -> new Point(coordinate.x, coordinate.y))
        .collect(Collectors.toList());
    return new AoiDto(aoi.getId(), aoi.getName(), area);
  }

}
