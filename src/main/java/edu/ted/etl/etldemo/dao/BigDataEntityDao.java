package edu.ted.etl.etldemo.dao;

import edu.ted.etl.etldemo.entity.BigDataEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BigDataEntityDao {
    @Transactional(readOnly = true)
    List<BigDataEntity> findAll();

    void saveAll(List<BigDataEntity> list);
}
