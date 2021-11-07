package edu.ted.etl.etldemo.service;

import edu.ted.etl.etldemo.dao.BigDataEntityJpaDao;
import edu.ted.etl.etldemo.entity.BigDataEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class BigDataEntityServiceImpl implements BigDataEntityService {

    @Autowired
    private BigDataEntityJpaDao bigDataEntityDao;

    @Transactional
    @Override
    public void findAllAndUpdateWithPageSize(int pageSize) {
        Page<BigDataEntity> page;
        int pageNumber = 0;
        do {
            PageRequest request = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());
            page = bigDataEntityDao.findAll(request);
            List<BigDataEntity> bigDataBatch = page.toList();
            log.info("{}: Next {} elements of BigData Entity", pageNumber, bigDataBatch.size());
            for (BigDataEntity bigDataEntity : bigDataBatch) {
                LocalDateTime newDate = LocalDateTime.now();
                log.info("current BigDataEntity with id {} has text field size {}b", bigDataEntity.getId(), bigDataEntity.getTextData().length());
                bigDataEntity.setUpdatedOn(newDate);
            }
            System.out.println("Big data rows number: " + bigDataBatch.size());
            bigDataEntityDao.saveAll(bigDataBatch);
            pageNumber++;
        } while (page.hasNext());
    }
}
