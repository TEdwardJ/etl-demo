package edu.ted.etl.etldemo;

import edu.ted.etl.etldemo.entity.BigDataEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Slf4j
class EtlTest {

    private static final int MAX_TEXT_DATA_SIZE = 512 * 1024;
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional()
    @Commit
    void loadBigDataIntoDB() {
        log.info("Start of loading");
        for (int i = 0; i < 500; i++) {
            BigDataEntity entity = new BigDataEntity();
            String bigTextFData = getBigRandomTextData();
            entity.setTextData(bigTextFData);
            //entity.setBinaryData(bigTextFData.getBytes());
            entityManager.persist(entity);
            log.info("current entity has been persisted " + entity.getId());
            if (i % 20 == 0) {
                entityManager.flush();
                log.info("current entity has been flushed");
            }
        }
        entityManager.flush();
    }

    private String getBigRandomTextData() {
        StringBuilder result = new StringBuilder("");
        while (result.length() < MAX_TEXT_DATA_SIZE) {
            result.append(UUID.randomUUID().toString().repeat(3));
        }
        return result.toString();
    }

    @Test
    public void testStringGeneration() {
        String bigString = getBigRandomTextData();
        assertTrue(bigString.length() >= MAX_TEXT_DATA_SIZE);
    }
}