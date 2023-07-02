package com.example.ncsproject.domain.user.domain.repository;

import com.example.ncsproject.domain.user.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
