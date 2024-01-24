package com.fourchannel.b.culturaLocale.repositories;

import com.fourchannel.b.culturaLocale.dataModels.Contest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ContestRepository extends CrudRepository<Contest,Long> {
    List<Contest> findByNameAndInitialDateBetweenAndType(String name, Date startDate, Date endDate, String type);
}
