package dev.springframework.repositories;

import dev.springframework.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * Created by jt on 1/10/17.
 */
public interface UserRepository extends CrudRepository<User, UUID> {
}
