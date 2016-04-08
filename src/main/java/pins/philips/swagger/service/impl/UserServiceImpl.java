package pins.philips.swagger.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pins.philips.swagger.dao.UserDAO;
import pins.philips.swagger.entity.UsersEntity;
import pins.philips.swagger.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;

	public UsersEntity get(int index) {
		// TODO Auto-generated method stub
		return userDAO.get(index);
	}

	public List<UsersEntity> getAll() {
		// TODO Auto-generated method stub
		return userDAO.getAll();
	}

	public void add(UsersEntity userEntity) throws Exception {
		// TODO Auto-generated method stub
		userDAO.add(userEntity);
	}

	public void delete(int index) throws Exception {
		// TODO Auto-generated method stub
		userDAO.delete(index);
	}

	public void update(int index, UsersEntity userEntity) throws Exception {
		// TODO Auto-generated method stub
		userDAO.update(index, userEntity);
	}

}
