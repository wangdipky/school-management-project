package vn.com.v4v.commonservice.service.impl;

import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.stereotype.Service;
import vn.com.v4v.commonservice.entity.MasterData;
import vn.com.v4v.commonservice.entity.QMasterData;
import vn.com.v4v.commonservice.service.IMasterDataService;

@Service
public class MasterDataServiceImpl implements IMasterDataService {

    @Override
    public void test() {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("vn.com.v4v.commonservice.entity");
        EntityManager em = emf.createEntityManager();
        JPAQueryFactory queryFactory = new JPAQueryFactory(JPQLTemplates.DEFAULT, em);

        QMasterData qMasterData = QMasterData.masterData;
        MasterData masterData = queryFactory.select(qMasterData)
                .fetchFirst();
        System.out.println(masterData.toString());
    }
}
