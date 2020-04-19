package com.stackabuse.dtoexample.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stackabuse.dtoexample.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

}
