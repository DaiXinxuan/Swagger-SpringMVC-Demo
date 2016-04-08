package pins.philips.swagger.dao.impl;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pins.philips.swagger.dao.UserDAO;
import pins.philips.swagger.entity.UsersEntity;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
    private SessionFactory sessionFactory;

    private Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }
	

	public UsersEntity get(int index) {
		// TODO Auto-generated method stub
		Session session = getSession();
		String hql = "from UsersEntity u where u.id=:id";
		List list = session.createQuery(hql).setInteger("id", index).list();
		if(list.size()>0) return (UsersEntity) list.get(0);
		else return null;
	}

	public List<UsersEntity> getAll() {
		// TODO Auto-generated method stub
		String hql="from UsersEntity";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		return query.list();
	}

	public void add(UsersEntity userEntity) throws Exception {
		// TODO Auto-generated method stub
		Session session=getSession();
        session.saveOrUpdate(userEntity);
        session.flush();
        session.evict(userEntity);
	}

	public void delete(int index) throws Exception {
		// TODO Auto-generated method stub
		Session session = getSession();
		UsersEntity usersEntity = get(index);
		if (usersEntity == null) {
			throw new NullPointerException();
		}
		String hql = "delete from UsersEntity u where u.id=:id";
		session.createQuery(hql).setInteger("id", index).executeUpdate();
	}

	public void update(int index, UsersEntity userEntity) throws Exception {
		// TODO Auto-generated method stub
		Session session = getSession();
		UsersEntity preUserEntity = get(index);
		preUserEntity.setName(userEntity.getName());
		preUserEntity.setPassword(userEntity.getPassword()); 
		session.saveOrUpdate(preUserEntity);
        session.flush();
        session.evict(preUserEntity);
	}

}
