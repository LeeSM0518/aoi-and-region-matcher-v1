package io.wisoft.aoiandregionmatcherv1.annotation;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
@IntegrationTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RepositoryTest {
}
