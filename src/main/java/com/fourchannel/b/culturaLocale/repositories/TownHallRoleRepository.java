package com.fourchannel.b.culturaLocale.repositories;

import com.fourchannel.b.culturaLocale.dataModels.users.Role;
import com.fourchannel.b.culturaLocale.dataModels.users.TownHallRoleUser;
import com.fourchannel.b.culturaLocale.dataModels.users.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TownHallRoleRepository extends CrudRepository<TownHallRoleUser,Long> {
    @Query("SELECT t FROM TownHallRoleUser t WHERE t.user.id = :userId")
    List<TownHallRoleUser> findTownHallRolesByUserId(@Param("userId") Long userId);
    @Query("SELECT tru.user FROM TownHallRoleUser tru WHERE tru.role = :role")
    List<User> findUserByRole(@Param("role")Role role);
}
