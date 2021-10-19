package edu.ted.etl.etldemo.dao;

import edu.ted.etl.etldemo.entity.BigDataEntity;
import edu.ted.etl.etldemo.service.BigDataEntityService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class BigDataEntityDaoTest {

    private BigDataEntityJpaDao bigDataEntityDao;
    @Autowired
    private BigDataEntityService bigDataEntityService;

    @Test
    void findAll() {
        List<BigDataEntity> all = bigDataEntityDao.findAll();
        System.out.println("Big data rows number: " + all.size());
        assertFalse(all.isEmpty());
        assertTrue(all.size() > 1);
    }

    @Test
    void findAllAndUpdate() {
        bigDataEntityService.findAllAndUpdateWithPageSize(50);

    }
}