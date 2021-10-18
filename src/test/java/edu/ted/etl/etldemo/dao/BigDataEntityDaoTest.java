package edu.ted.etl.etldemo.dao;

import edu.ted.etl.etldemo.entity.BigDataEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class BigDataEntityDaoTest {

    @Autowired
    private BigDataEntityDao bigDataEntityDao;

    @Test
    void findAll() {
        List<BigDataEntity> all = bigDataEntityDao.findAll();
        System.out.println("Big data rows number: " + all.size());
        assertFalse(all.isEmpty());
        assertTrue(all.size() > 1);
    }

    @Test
    //@Transactional(readOnly = true)
    void findAllAndUpdate() {
        List<BigDataEntity> all = bigDataEntityDao.findAll();
        System.out.println("Big data rows number: " + all.size());
        assertFalse(all.isEmpty());
        assertTrue(all.size() > 1);
        LocalDateTime newDate = LocalDateTime.now();
        for (BigDataEntity bigDataEntity : all) {
            log.info("current BigDataEntity with id {} has text field size {}b", bigDataEntity.getId(), bigDataEntity.getTextData().length());
            bigDataEntity.setUpdatedOn(newDate);
        }
        bigDataEntityDao.saveAll(all);
    }
}