package io.wisoft.aoiandregionmetcherv1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class HibernateTest {

  @Autowired
  EntityManager entityManager;

  @Test
  void createTableTest() {
    final Query createTableQuery =
        entityManager.createNativeQuery("CREATE TABLE test (name TEXT, area GEOMETRY('Polygon', 4326))");
    createTableQuery.executeUpdate();
  }

}
