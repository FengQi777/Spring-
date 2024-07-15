package com.example.demo.data;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.Users;

public interface UserRepository extends CrudRepository<Users , Long> {

  Users findByUsername(String username);

}