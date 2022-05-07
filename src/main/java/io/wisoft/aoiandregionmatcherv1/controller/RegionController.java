package io.wisoft.aoiandregionmatcherv1.controller;

import io.wisoft.aoiandregionmatcherv1.dto.AoiDto;
import io.wisoft.aoiandregionmatcherv1.dto.FindAoisIntersectRegionRequest;
import io.wisoft.aoiandregionmatcherv1.dto.FindAoisIntersectRegionResponse;
import io.wisoft.aoiandregionmatcherv1.dto.RegisterRegionRequest;
import io.wisoft.aoiandregionmatcherv1.dto.RegisterRegionResponse;
import io.wisoft.aoiandregionmatcherv1.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/regions")
@RequiredArgsConstructor
public class RegionController {

  private final RegionService regionService;

  @PostMapping
  public RegisterRegionResponse register(@RequestBody @Valid RegisterRegionRequest request) {
    final Integer regionId = regionService.register(request);
    return new RegisterRegionResponse(regionId);
  }

  @GetMapping("/{regionId}/aois/intersects")
  public FindAoisIntersectRegionResponse findAoisIntersectRegion(@PathVariable("regionId") Integer regionId) {
    final FindAoisIntersectRegionRequest request = new FindAoisIntersectRegionRequest(regionId);
    final List<AoiDto> aois = regionService
        .findAoisIntersectRegion(request)
        .stream()
        .map(AoiDto::fromEntity)
        .collect(toList());
    return new FindAoisIntersectRegionResponse(aois);
  }


}
