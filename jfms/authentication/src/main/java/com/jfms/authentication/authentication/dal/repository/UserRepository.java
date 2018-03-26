package com.jfms.authentication.authentication.dal.repository;

import com.jfms.authentication.authentication.dal.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
