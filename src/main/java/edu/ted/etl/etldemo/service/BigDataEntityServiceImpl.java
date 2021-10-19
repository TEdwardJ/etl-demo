package edu.ted.etl.etldemo.service;

import edu.ted.etl.etldemo.dao.BigDataEntityJpaDao;
import edu.ted.etl.etldemo.entity.BigDataEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class BigDataEntityServiceImpl implements BigDataEntityService {

    @Autowired
    private BigDataEntityJpaDao bigDataEntityDao;

    @Override
    public void findAllAndUpdateWithPageSize(int pageSize) {
        Page<BigDataEntity> page;
        int pageNumber = 0;
        do {
            PageRequest request = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());
            page = bigDataEntityDao.findAll(request);
            log.info("Next {} elements of BigData Entity", page.toList().size());
            for (BigDataEntity bigDataEntity : page.toList()) {
                LocalDateTime newDate = LocalDateTime.now();
                log.info("current BigDataEntity with id {} has text field size {}b", bigDataEntity.getId(), bigDataEntity.getTextData().length());
                bigDataEntity.setUpdatedOn(newDate);
            }
            System.out.println("Big data rows number: " + page.toList().size());
            bigDataEntityDao.saveAll(page.toList());
            pageNumber++;
        } while (page.hasNext());
    }
}
