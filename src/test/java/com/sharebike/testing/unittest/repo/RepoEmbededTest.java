package com.sharebike.testing.unittest.repo;

import com.sharebike.testing.entity.TestEntity;
import com.sharebike.testing.repo.TestRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class RepoEmbededTest {

    @Autowired
    TestRepo testRepo;

    @Test
    public void shouldSaveEntity() {
        TestEntity testEntity = new TestEntity(null, "Tahsin", 24, 5.8);
        TestEntity savedEntity = testRepo.save(testEntity);
        assertThat(savedEntity).usingRecursiveComparison().ignoringFields("id").isEqualTo(testEntity);
    }

    @Test
    @Sql("classpath:test-data.sql")
    public void shouldSaveEntityThroughSql() {
        Optional<TestEntity> testEntity = testRepo.findByName("SAJAL");
        assertThat(testEntity).isNotEmpty();
    }
}
