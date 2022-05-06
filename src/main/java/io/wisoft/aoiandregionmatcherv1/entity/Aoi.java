package io.wisoft.aoiandregionmatcherv1.entity;

import io.wisoft.aoiandregionmatcherv1.dto.Point;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Polygon;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

import static io.wisoft.aoiandregionmatcherv1.entity.factory.PolygonFactory.createPolygon;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
@Getter
public class Aoi {

  @Id
  @GeneratedValue(generator = "increment")
  @Column(name = "aoi_id", nullable = false)
  private Integer id;

  @Column(nullable = false)
  private String name;

  @Column(columnDefinition = "geometry(Point, 4326)", nullable = false)
  private Polygon area;

  public Aoi(final String name, final List<Point> area) {
    this.name = name;
    this.area = createPolygon(area);
  }

  public interface AoiRow {

    Integer getId();
    String getName();
    String getAreaText();
    default Aoi toEntity() {
      final Polygon area = createPolygon(getAreaText());
      return new Aoi(getId(), getName(), area);
    }

  }

}
