package io.wisoft.aoiandregionmatcherv1.annotation;

import org.springframework.boot.test.context.SpringBootTest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@IntegrationTest
@SpringBootTest(webEnvironment = RANDOM_PORT)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface WebLayerTest {
}
