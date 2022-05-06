package io.wisoft.aoiandregionmatcherv1.repository;

import io.wisoft.aoiandregionmatcherv1.entity.Aoi;
import io.wisoft.aoiandregionmatcherv1.entity.Aoi.AoiRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AoiRepository extends JpaRepository<Aoi, Integer> {

  @Query(value = "SELECT a.aoi_id AS id, " +
      "a.name, " +
      "CAST(st_astext(a.area) AS VARCHAR) as areaText " +
      "FROM aoi a, st_setsrid(st_point(:x, :y), 4326) p " +
      "ORDER BY st_distance(p, a.area) " +
      "LIMIT 1", nativeQuery = true)
  AoiRow findAoiClosestToPoint(@Param(value = "x") Double x, @Param(value = "y") Double y);

}
