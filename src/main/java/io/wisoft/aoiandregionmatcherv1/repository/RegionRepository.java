package io.wisoft.aoiandregionmatcherv1.repository;

import io.wisoft.aoiandregionmatcherv1.entity.Aoi.AoiRow;
import io.wisoft.aoiandregionmatcherv1.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {

  @Query(value =
      "SELECT a.aoi_id, a.name, CAST(st_astext(a.area) AS VARCHAR) AS areaText " +
          "FROM aoi a, region r " +
          "WHERE r.region_id = :id " +
          "AND st_intersects(r.area, a.area)"
      , nativeQuery = true)
  List<AoiRow> findAoisIntersectRegion(@Param(value = "id") Integer regionId);

}
