package io.wisoft.aoiandregionmatcherv1.controller;

import io.wisoft.aoiandregionmatcherv1.dto.RegisterAoiRequest;
import io.wisoft.aoiandregionmatcherv1.dto.RegisterAoiResponse;
import io.wisoft.aoiandregionmatcherv1.service.AoiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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


}
