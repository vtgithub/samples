package com.jfms.aaa.dal.repository;


import com.jfms.aaa.dal.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
