package com.farzi.eng.farziweb.repository;

import com.farzi.eng.farziweb.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value ="SELECT * FROM user where username = :userName", nativeQuery = true)
    User findUsername(@Param("userName")String userName);
}
