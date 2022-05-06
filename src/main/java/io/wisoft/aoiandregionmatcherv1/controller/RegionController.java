package io.wisoft.aoiandregionmatcherv1.controller;

import io.wisoft.aoiandregionmatcherv1.dto.AoiDto;
import io.wisoft.aoiandregionmatcherv1.dto.FindAoisIncludedRegionRequest;
import io.wisoft.aoiandregionmatcherv1.dto.FindAoisIncludedRegionResponse;
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
  public FindAoisIncludedRegionResponse findAoisIncludedRegion(@PathVariable("regionId") Integer regionId) {
    final FindAoisIncludedRegionRequest request = new FindAoisIncludedRegionRequest(regionId);
    final List<AoiDto> aois = regionService
        .findAoisIncludedRegion(request)
        .stream()
        .map(AoiDto::fromEntity)
        .collect(toList());
    return new FindAoisIncludedRegionResponse(aois);
  }


}
