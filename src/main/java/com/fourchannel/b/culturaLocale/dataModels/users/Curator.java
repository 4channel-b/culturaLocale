package com.fourchannel.b.culturaLocale.dataModels.users;

import com.fourchannel.b.culturaLocale.dataModels.ApprovalStatus;
import com.fourchannel.b.culturaLocale.dataModels.Content;
import com.fourchannel.b.culturaLocale.repositories.IVectorRepository;

public class Curator extends IUser {
    public void approvePendingContent(IVectorRepository<Content> repository, Content content) {
        content.setStatus(ApprovalStatus.ACCEPTED);
        repository.update(content);
    }
}
