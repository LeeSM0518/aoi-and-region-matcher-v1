package io.wisoft.aoiandregionmatcherv1.repository;

import io.wisoft.aoiandregionmatcherv1.entity.Aoi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AoiRepository extends JpaRepository<Aoi, Integer> {
}
