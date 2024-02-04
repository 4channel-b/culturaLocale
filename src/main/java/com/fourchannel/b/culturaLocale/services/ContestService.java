package com.fourchannel.b.culturaLocale.services;

import com.fourchannel.b.culturaLocale.dataModels.Contest;

import java.util.List;

public interface ContestService {
    Contest createContest(Contest contest, List<Long> contents);
    Contest getContest(Long id);
    List<Contest> getAllContest();
    void updateContest(Contest contest, List<Long> contents);

    void subscribeContent(Long contentId, Long contestId);

    void terminateContest(Long contestId, Long winningContentId);
}
