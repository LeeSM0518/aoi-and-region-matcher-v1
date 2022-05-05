package io.wisoft.aoiandregionmatcherv1.entity;

import io.wisoft.aoiandregionmatcherv1.dto.Point;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Polygon;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

import static io.wisoft.aoiandregionmatcherv1.entity.factory.PolygonFactory.createPolygon;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Region {

  @Id
  @GeneratedValue(generator = "increment")
  @Column(name = "region_id")
  private Integer id;

  private String name;

  @Column(columnDefinition = "geometry(Point, 4326)")
  private Polygon area;

  public Region(final String name, final List<Point> area) {
    this.name = name;
    this.area = createPolygon(area);
  }

}
