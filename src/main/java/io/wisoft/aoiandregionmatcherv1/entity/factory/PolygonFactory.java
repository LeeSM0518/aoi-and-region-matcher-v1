package io.wisoft.aoiandregionmatcherv1.entity.factory;

import io.wisoft.aoiandregionmatcherv1.dto.Point;
import io.wisoft.aoiandregionmatcherv1.exception.RingNotClosedException;
import io.wisoft.aoiandregionmatcherv1.exception.StringToPolygonParseException;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PolygonFactory {
  private static final GeometryFactory geometryFactory = new GeometryFactory();
  private static final WKTReader reader = new WKTReader();

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

  public static Polygon createPolygon(String polygon) {
    try {
      return (Polygon) reader.read(polygon);
    } catch (ParseException exception) {
      throw new StringToPolygonParseException();
    }
  }

}
