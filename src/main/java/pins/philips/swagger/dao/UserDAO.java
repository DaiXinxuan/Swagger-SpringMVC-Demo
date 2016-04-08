package pins.philips.swagger.dao;



import pins.philips.swagger.entity.UsersEntity;

import java.util.List;

public interface UserDAO {
	
	public UsersEntity get(int index);
	
	public List<UsersEntity> getAll();
	
	public void add(UsersEntity userEntity) throws Exception;
	
	public void delete(int index) throws Exception;
	
	public void update(int index, UsersEntity userEntity) throws Exception;
}
