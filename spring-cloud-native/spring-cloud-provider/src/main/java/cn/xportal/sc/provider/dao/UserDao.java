package cn.xportal.sc.provider.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.xportal.sc.provider.entity.UserEntity;


@Repository
public interface UserDao extends JpaRepository<UserEntity, Long> {

}