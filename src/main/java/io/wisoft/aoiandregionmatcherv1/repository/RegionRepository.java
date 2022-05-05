package io.wisoft.aoiandregionmatcherv1.repository;

import io.wisoft.aoiandregionmatcherv1.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
}
