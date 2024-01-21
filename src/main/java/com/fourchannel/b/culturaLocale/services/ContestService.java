package com.fourchannel.b.culturaLocale.services;

import com.fourchannel.b.culturaLocale.dataModels.Contest;

import java.util.List;

public interface ContestService {
    Contest createContest(Contest contest);
    Contest getContest(Long id);
    List<Contest> getAllContest();
    void updateContest(Contest contest);

}
