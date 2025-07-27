package vn.com.v4v.common;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * Name: AbstractService
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 27/07/2025
 * */
public class AbstractService {

    @PersistenceContext
    private EntityManager em;

    protected JPAQueryFactory getQueryFactory() {
        return new JPAQueryFactory(em);
    }

}