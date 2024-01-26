package com.fourchannel.b.culturaLocale.repositories;

import com.fourchannel.b.culturaLocale.dataModels.TownHall;
import com.fourchannel.b.culturaLocale.dataModels.users.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,Long>
{
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.username = :username OR u.email = :email")
    boolean exists(@Param("username") String username, @Param("email") String email);
    @Query("SELECT u FROM User u WHERE u.email = :email")
    List<User> findByEmail(@Param("email")String email);
}
