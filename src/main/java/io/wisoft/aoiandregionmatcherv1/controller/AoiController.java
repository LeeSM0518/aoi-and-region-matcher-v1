package io.wisoft.aoiandregionmatcherv1.controller;

import io.wisoft.aoiandregionmatcherv1.dto.FindAoiNearestFromPointRequest;
import io.wisoft.aoiandregionmatcherv1.dto.FindAoiNearestFromPointResponse;
import io.wisoft.aoiandregionmatcherv1.dto.RegisterAoiRequest;
import io.wisoft.aoiandregionmatcherv1.dto.RegisterAoiResponse;
import io.wisoft.aoiandregionmatcherv1.entity.Aoi;
import io.wisoft.aoiandregionmatcherv1.service.AoiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/aois")
@RequiredArgsConstructor
public class AoiController {

  private final AoiService aoiService;

  @PostMapping
  public RegisterAoiResponse register(@RequestBody @Valid RegisterAoiRequest request) {
    final Integer aoiId = aoiService.register(request);
    return new RegisterAoiResponse(aoiId);
  }

  @GetMapping
  public FindAoiNearestFromPointResponse findAoiNearestFromPoint(
      @RequestParam(name = "lat") Double latitude,
      @RequestParam(name = "long") Double longitude) {
    final FindAoiNearestFromPointRequest request = new FindAoiNearestFromPointRequest(latitude, longitude);
    final Aoi aoi = aoiService.findAoiNearestFromPoint(request);
    return new FindAoiNearestFromPointResponse(aoi);
  }

}
