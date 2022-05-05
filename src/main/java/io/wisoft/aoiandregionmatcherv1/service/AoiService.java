package io.wisoft.aoiandregionmatcherv1.service;

import io.wisoft.aoiandregionmatcherv1.dto.RegisterAoiRequest;
import io.wisoft.aoiandregionmatcherv1.entity.Aoi;
import io.wisoft.aoiandregionmatcherv1.repository.AoiRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AoiService {

  private final AoiRepository aoiRepository;

  @Transactional
  public Integer register(RegisterAoiRequest request) {
    final Aoi aoi = new Aoi(request.getName(), request.getArea());
    final Aoi savedAoi = aoiRepository.save(aoi);
    return savedAoi.getId();
  }

}
