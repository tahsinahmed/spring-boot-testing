package com.sharebike.testing.service;

import com.sharebike.testing.entity.TestEntity;
import com.sharebike.testing.repo.TestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    TestRepo repo;

    public List<TestEntity> getAll() {
        return repo.findAll();
    }

    public String create(TestEntity testEntity) {
        repo.save(testEntity);
        return "Saved Successfully";
    }

    public String update(TestEntity testEntity) {
        repo.save(testEntity);
        return "Updated Successfully";
    }

    public TestEntity getOne(Long id) {
        return repo.findById(id).get();
    }

    public String delete(Long id) {
        repo.deleteById(id);
        return "Successfully Deleted";
    }
}
