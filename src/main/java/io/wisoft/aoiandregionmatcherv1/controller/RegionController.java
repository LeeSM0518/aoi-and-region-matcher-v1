package io.wisoft.aoiandregionmatcherv1.controller;

import io.wisoft.aoiandregionmatcherv1.dto.RegisterRegionRequest;
import io.wisoft.aoiandregionmatcherv1.dto.RegisterRegionResponse;
import io.wisoft.aoiandregionmatcherv1.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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


}
