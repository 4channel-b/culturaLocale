package com.fourchannel.b.culturaLocale.repositories;

import com.fourchannel.b.culturaLocale.dataModels.Content;
import com.fourchannel.b.culturaLocale.dataModels.Contest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ContentRepository extends CrudRepository<Content,Long> {
    List<Content> findByNameAndDescriptionAndCreationDate(String name, String description, Date creationDate);
}