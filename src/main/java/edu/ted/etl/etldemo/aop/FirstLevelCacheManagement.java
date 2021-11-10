package edu.ted.etl.etldemo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Aspect
@Component
@Slf4j
public class FirstLevelCacheManagement {

    @Value("${AOP_CACHE_MANAGEMENT_ENABLED:true}")
    private boolean aopCacheManagement;

    @PersistenceContext
    private EntityManager entityManager;

    public FirstLevelCacheManagement() {
        log.info("Aspect based FirstLevelCacheManagement class created");
    }

    @AfterReturning("execution(* edu.ted.etl.etldemo.dao.BigDataEntityJpaDao.saveAll(..))")
    public void clearCache() {
        log.info("aopCacheManagement = "+aopCacheManagement);
        if (aopCacheManagement) {
            var session = entityManager.unwrap(Session.class);
            entityManager.flush();
            session.getSession().clear();
            log.info("session cache is flushed and cleared;");
        }
    }
}
