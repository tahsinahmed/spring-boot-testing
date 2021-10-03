package com.sharebike.testing.unittest.repo;

import com.sharebike.testing.TestingApplication;
import com.sharebike.testing.entity.TestEntity;
import com.sharebike.testing.repo.TestRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RepoTestContainer extends ContainersEnvironment {

    @Autowired
    TestRepo testRepo;

    @Test
    public void shouldSaveEntity() {
        TestEntity testEntity = new TestEntity(null, "Tahsin", 24, 5.8);
        TestEntity savedEntity = testRepo.save(testEntity);
        assertThat(savedEntity).usingRecursiveComparison()
                .ignoringFields("id").isEqualTo(testEntity);
    }
}
