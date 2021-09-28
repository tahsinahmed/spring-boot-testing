package com.sharebike.testing.controller;

import com.sharebike.testing.entity.TestEntity;
import com.sharebike.testing.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class TestController {

    @Autowired
    TestService service;

    @GetMapping("/test")
    public List<TestEntity> getAll() {
        return service.getAll();
    }

    @PostMapping("/test")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody TestEntity testEntity) {
        return service.create(testEntity);
    }

    @PutMapping("/test")
    public String update(@RequestBody TestEntity testEntity) {
        return service.update(testEntity);
    }

    @GetMapping("/test-one")
    public TestEntity getOne(@RequestParam(value = "id") Long id) {
        return service.getOne(id);
    }

    @DeleteMapping("/test")
    public String delete(@RequestParam(value = "id") Long id) {
        return service.delete(id);
    }
}
