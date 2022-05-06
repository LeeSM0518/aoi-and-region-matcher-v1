package io.wisoft.aoiandregionmatcherv1.service;

import io.wisoft.aoiandregionmatcherv1.dto.FindAoisIncludedRegionRequest;
import io.wisoft.aoiandregionmatcherv1.dto.RegisterRegionRequest;
import io.wisoft.aoiandregionmatcherv1.entity.Aoi;
import io.wisoft.aoiandregionmatcherv1.entity.Aoi.AoiRow;
import io.wisoft.aoiandregionmatcherv1.entity.Region;
import io.wisoft.aoiandregionmatcherv1.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class RegionService {

  private final RegionRepository regionRepository;

  @Transactional
  public Integer register(RegisterRegionRequest request) {
    final Region region = new Region(request.getName(), request.getArea());
    final Region savedRegion = regionRepository.save(region);
    return savedRegion.getId();
  }

  public List<Aoi> findAoisIncludedRegion(FindAoisIncludedRegionRequest request) {
    return regionRepository
        .findAoisIncludedRegion(request.getRegionId())
        .stream()
        .map(AoiRow::toEntity)
        .collect(toList());
  }

}
