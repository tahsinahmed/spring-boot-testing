package com.sharebike.testing.unittest.service;

import com.sharebike.testing.entity.TestEntity;
import com.sharebike.testing.repo.TestRepo;
import com.sharebike.testing.service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ServiceTest {

    @Autowired
    TestService testService;

    @MockBean
    TestRepo testRepo;

    @Test
    public void getAllTest() {
        when(testRepo.findAll()).thenReturn(Stream.of(new TestEntity(1L, "Tahsin", 24, 5.8),
                new TestEntity(2L, "Irtiza", 24, 5.7)).collect(Collectors.toList()));
        assertEquals(3, testService.getAll().size());
    }

    @Test
    public void getOneTest() {
        Long id = 1L;
        when(testRepo.findById(id)).thenReturn(Optional.of(new TestEntity(1L, "Tahsin", 24, 5.8)));
        assertEquals(24, testService.getOne(id).getAge());
    }

    @Test
    public void saveTest() {
        TestEntity testEntity = new TestEntity(1L, "Rafik", 27, 5.9);
        when(testRepo.save(testEntity)).thenReturn(testEntity);
        assertEquals("Saved Successfully!!", testService.create(testEntity));
    }
}
