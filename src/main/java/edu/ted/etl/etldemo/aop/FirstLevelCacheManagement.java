package edu.ted.etl.etldemo.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Aspect
@Component
public class FirstLevelCacheManagement {

    //AOP_CACHE_MANAGEMENT_ENABLED
    @Value("${AOP_CACHE_MANAGEMENT_ENABLED:true}")
    private boolean aopCacheManagement;

    @PersistenceContext
    private EntityManager entityManager;

    public FirstLevelCacheManagement() {
        System.out.println(" aspect test");
    }

    @AfterReturning("execution(* edu.ted.etl.etldemo.dao.BigDataEntityJpaDao.saveAll(..))")
    public void clearCache() {
        if (aopCacheManagement) {
            var sessionFactory = entityManager.unwrap(Session.class);
            entityManager.flush();
            sessionFactory.getSession().clear();
            System.out.println("session cache is flushed and cleared;");
        }
    }
}
