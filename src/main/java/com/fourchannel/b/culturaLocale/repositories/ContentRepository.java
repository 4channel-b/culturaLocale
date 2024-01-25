package com.fourchannel.b.culturaLocale.repositories;

import com.fourchannel.b.culturaLocale.dataModels.Content;
import com.fourchannel.b.culturaLocale.dataModels.Contest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ContentRepository extends CrudRepository<Content,Long> {
    @Query("SELECT c FROM Content c WHERE c.name = :name AND c.description = :description AND c.creationDate = :creationDate")
    List<Content> findByNameAndDescriptionAndCreationDate(
            @Param("name") String name,
            @Param("description") String description,
            @Param("creationDate") Date creationDate);
}