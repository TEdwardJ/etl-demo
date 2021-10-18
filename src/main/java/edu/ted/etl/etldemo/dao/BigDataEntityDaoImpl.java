package edu.ted.etl.etldemo.dao;

import edu.ted.etl.etldemo.entity.BigDataEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class BigDataEntityDaoImpl implements BigDataEntityDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<BigDataEntity> findAll() {
        TypedQuery<BigDataEntity> query = entityManager.createQuery("select b from BigDataEntity b", BigDataEntity.class);
        List<BigDataEntity> resultList = query.getResultList();
        return resultList;
    }

    @Override
    @Transactional
    public void saveAll(List<BigDataEntity> list) {
        list.forEach(e->entityManager.merge(e));
        entityManager.flush();
    }

}
