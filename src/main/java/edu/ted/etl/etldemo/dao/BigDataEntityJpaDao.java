package edu.ted.etl.etldemo.dao;

import edu.ted.etl.etldemo.entity.BigDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface BigDataEntityJpaDao extends JpaRepository<BigDataEntity, Long> {

    List<BigDataEntity> findAll();
    //List<BigDataEntity> findAll(Pageable pageable);
}
