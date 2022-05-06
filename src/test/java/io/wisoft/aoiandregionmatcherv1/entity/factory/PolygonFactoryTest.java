package io.wisoft.aoiandregionmatcherv1.entity.factory;

import io.wisoft.aoiandregionmatcherv1.dto.Point;
import io.wisoft.aoiandregionmatcherv1.exception.RingNotClosedException;
import io.wisoft.aoiandregionmatcherv1.exception.StringToPolygonParseException;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Polygon;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class PolygonFactoryTest {

  @Test
  void createPolygonFromPointsTest() {
    final Point point1 = new Point(1, 2);
    final Point point2 = new Point(3, 4);
    final Point point3 = new Point(1, 2);
    final List<Point> points = List.of(point1, point2, point3);

    final Polygon polygon = PolygonFactory.createPolygon(points);

    final Coordinate[] coordinates = polygon.getCoordinates();

    for (int i = 0; i < points.size(); i++) {
      assertThat(coordinates[i].x).isEqualTo(points.get(i).getX());
      assertThat(coordinates[i].y).isEqualTo(points.get(i).getY());
    }
  }

  @Test
  void failToCreatePolygonByRingNotClosed() {
    final Point point1 = new Point(1, 2);
    final Point point2 = new Point(3, 4);
    final List<Point> points = List.of(point1, point2);

    assertThatExceptionOfType(RingNotClosedException.class)
        .isThrownBy(() -> PolygonFactory.createPolygon(points));
  }

  @Test
  void createPolygonFromStringTest() {
    String areaText = "Polygon((1 1, 2 3, 3 4, 4 5, 1 1))";
    final Polygon polygon = PolygonFactory.createPolygon(areaText);
    assertThat(polygon).isInstanceOf(Polygon.class);
    assertThat(polygon).isNotNull();
  }

  @Test
  void failToCreatePolygonByStringToPolygonParse() {
    String areaText = "Polygon";

    assertThatExceptionOfType(StringToPolygonParseException.class)
        .isThrownBy(() -> PolygonFactory.createPolygon(areaText));
  }

}