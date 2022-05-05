package io.wisoft.aoiandregionmatcherv1.entity.factory;

import io.wisoft.aoiandregionmatcherv1.dto.Point;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateSequence;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PolygonFactory {
  private static final GeometryFactory geometryFactory = new GeometryFactory();

  public static Polygon createPolygon(List<Point> points) {
    try {
      final LinearRing linear = geometryFactory.createLinearRing(points
          .stream()
          .map((point) -> new Coordinate(point.getX(), point.getY())).toArray(Coordinate[]::new));
      return new Polygon(linear, null, geometryFactory);
    } catch (IllegalArgumentException exception) {
      throw new RingNotClosedException();
    }
  }

}
