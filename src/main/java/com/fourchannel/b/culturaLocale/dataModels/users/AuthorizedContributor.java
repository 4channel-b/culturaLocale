package com.fourchannel.b.culturaLocale.dataModels.users;

import com.fourchannel.b.culturaLocale.dataModels.ApprovalStatus;
import com.fourchannel.b.culturaLocale.dataModels.Content;
import com.fourchannel.b.culturaLocale.repositories.IVectorRepository;

public class AuthorizedContributor extends IUser {
    public void createApprovedContent(IVectorRepository<Content> repository, Content content) {
        if (content.getStatus() != ApprovalStatus.PENDING) {
            content.setStatus(ApprovalStatus.ACCEPTED);
        }

        repository.add(content);
    }
}
