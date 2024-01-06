package com.fourchannel.b.culturaLocale.dataModels.users;

import com.fourchannel.b.culturaLocale.dataModels.Content;
import com.fourchannel.b.culturaLocale.dataModels.Contest;
import com.fourchannel.b.culturaLocale.repositories.IVectorRepository;

public class Animator extends IUser {
    public void createContest(IVectorRepository<Contest> repository, Contest contest) {
        //repository.add(contest);
    }
}
