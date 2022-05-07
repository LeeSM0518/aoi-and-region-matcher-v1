package io.wisoft.aoiandregionmatcherv1;

import io.wisoft.aoiandregionmatcherv1.annotation.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@IntegrationTest
@SpringBootTest
class AoiAndRegionMatcherV1ApplicationTest {

  @Test
  void contextLoads() {
    AoiAndRegionMatcherV1Application.main(new String[]{""});
  }

}