package io.wisoft.aoiandregionmatcherv1;

import io.wisoft.aoiandregionmatcherv1.annotation.RepositoryTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@RepositoryTest
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
