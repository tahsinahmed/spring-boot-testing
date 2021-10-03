package com.sharebike.testing.repo;

import com.sharebike.testing.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestRepo extends JpaRepository<TestEntity, Long> {
    Optional<TestEntity> findByName(String name);
}
