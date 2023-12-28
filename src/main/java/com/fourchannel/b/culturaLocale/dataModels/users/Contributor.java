package com.fourchannel.b.culturaLocale.dataModels.users;

import com.fourchannel.b.culturaLocale.dataModels.ApprovalStatus;
import com.fourchannel.b.culturaLocale.dataModels.Content;
import com.fourchannel.b.culturaLocale.repositories.IVectorRepository;

public class Contributor extends IUser {
    public void createPendingContent(IVectorRepository<Content> repository, Content content) {
        if (content.getStatus() != ApprovalStatus.PENDING) {
            // Sanitizing.
            content.setStatus(ApprovalStatus.PENDING);
        }

        repository.add(content);
    }
}
