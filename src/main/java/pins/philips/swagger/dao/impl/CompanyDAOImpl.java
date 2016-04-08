package pins.philips.swagger.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pins.philips.swagger.dao.CompanyDAO;
import pins.philips.swagger.entity.CompanyEntity;

import java.util.List;

/**
 * Created by 310231492 on 2016/4/5.
 */
@Repository
public class CompanyDAOImpl implements CompanyDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }

    public CompanyEntity getCompany(int index) {
        Session session = getSession();
        String hql = "from CompanyEntity c where c.id=:id";
        List list = session.createQuery(hql).setInteger("id", index).list();
        if(list.size()>0) return (CompanyEntity) list.get(0);
        else return null;
    }


    public List<CompanyEntity> getAllCompanies() {
        String hql="from CompanyEntity";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hql);
        return query.list();
    }

    public void addCompany(CompanyEntity companyEntity) {
        Session session = getSession();
        session.saveOrUpdate(companyEntity);
        session.flush();
        session.evict(companyEntity);
    }

    public void updateCompany(int index, CompanyEntity companyEntity) {
        Session session = getSession();
        CompanyEntity companyEntity1 = getCompany(index);
        companyEntity1.setCompany(companyEntity.getCompany());
        companyEntity1.setType(companyEntity.getType());
        session.saveOrUpdate(companyEntity1);
        session.flush();
        session.evict(companyEntity1);
    }
}
