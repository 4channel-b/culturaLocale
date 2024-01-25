package com.fourchannel.b.culturaLocale.repositories;

import com.fourchannel.b.culturaLocale.dataModels.Contest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ContestRepository extends CrudRepository<Contest,Long> {
    @Query("SELECT c FROM Contest c WHERE c.name = :name AND c.initialDate BETWEEN :startDate AND :endDate AND c.type = :type")
    List<Contest> findByNameAndInitialDateBetweenAndType(
            @Param("name") String name,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate,
            @Param("type") String type);
}
