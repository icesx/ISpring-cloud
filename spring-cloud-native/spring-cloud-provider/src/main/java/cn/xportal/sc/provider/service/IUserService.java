package cn.xportal.sc.provider.service;

import java.util.List;

import cn.xportal.sc.provider.entity.UserEntity;

public interface IUserService {

	List<UserEntity> getUsers();

	void delete(Long id);

	UserEntity getUser(Long id);

	void save(UserEntity user);

	void update(UserEntity user);

}
