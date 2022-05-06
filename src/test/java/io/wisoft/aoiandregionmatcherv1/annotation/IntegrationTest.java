package io.wisoft.aoiandregionmatcherv1.annotation;

import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Testcontainers
@Sql({"/test-schema.sql", "/test-data.sql"})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IntegrationTest {
}
