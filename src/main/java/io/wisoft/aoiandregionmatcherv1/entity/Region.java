package io.wisoft.aoiandregionmatcherv1.entity;

import io.wisoft.aoiandregionmatcherv1.dto.Point;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.locationtech.jts.geom.Polygon;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigInteger;
import java.util.List;

import static io.wisoft.aoiandregionmatcherv1.entity.factory.PolygonFactory.createPolygon;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class Region {

  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "region_id_increment", strategy = "increment")
  @Column(name = "region_id")
  private BigInteger id;

  private String name;

  @Column(columnDefinition = "geometry(Point, 4326)")
  private Polygon area;

  public Region(final String name, final List<Point> points) {
    this.name = name;
    this.area = createPolygon(points);
  }

}
