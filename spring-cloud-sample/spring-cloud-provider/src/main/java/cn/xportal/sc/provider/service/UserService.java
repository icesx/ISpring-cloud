package cn.xportal.sc.provider.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import cn.xportal.sc.provider.dao.UserDao;
import cn.xportal.sc.provider.entity.UserEntity;

@Transactional
@Component
public class UserService implements IUserService {
	@Autowired
	private UserDao userDao;

	@Override
	public List<UserEntity> getUsers() {
		return userDao.findAll();
	}

	@Override
	public UserEntity getUser(Long id) {
		UserEntity user = userDao.findById(id).orElse(UserEntity.empty());
		return user;
	}

	@Override
	public void save(UserEntity user) {
		userDao.save(user);
	}

	@Override
	public void update(UserEntity user) {
		userDao.save(user);
	}

	@Override
	public void delete(@PathVariable("id") Long id) {
		userDao.deleteById(id);
	}

}
