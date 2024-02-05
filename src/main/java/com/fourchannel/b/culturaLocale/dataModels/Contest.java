package com.fourchannel.b.culturaLocale.dataModels;


import com.fourchannel.b.culturaLocale.dataModels.DTOs.ContestCreationRequestDTO;
import com.fourchannel.b.culturaLocale.dataModels.DTOs.EventCreationRequestDTO;
import com.fourchannel.b.culturaLocale.dataModels.users.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name="contest")
public class Contest
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contest_id_seq")
    private Long id;
    private String name;
    private String description;
    private Date initialDate;
    private Date endDate;
    private String rules;
    @Getter
    private String type;
    @ManyToMany
    private List<Content> contents;
    @ManyToOne
    private Content winningContent;
    private boolean contestOpen;

    public Contest(ContestCreationRequestDTO dto) {
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.initialDate = dto.getInitialDate();
        this.endDate = dto.getEndDate();
        this.rules = dto.getRules();
        this.type = dto.getType();
        this.contents = new ArrayList<>();
        this.contestOpen = true;
    }

    public void subscribe(Content content) {
        // make sure it's not here already
        for (Content c : contents) {
            if (c.getId().equals(content.getId())) {
                throw new IllegalStateException("Content already subscribed to contest.");
            }
        }

        this.contents.add(content);
    }
    public Set<Long> closeContest(Long winnerContentId) {
        if (!this.contestOpen) {
            throw new IllegalStateException("Contest is already closed.");
        }

        this.contestOpen = false;
        this.winningContent = this.contents.stream()
                .filter(c -> c.getId().equals(winnerContentId))
                .findFirst().orElse(null);

        if (this.winningContent == null) {
            this.contestOpen = true;
            throw new IllegalStateException("Contest is already closed.");
        }

        // Get a list from all contents in the objects
        // of losing user ids that are not equal to winnerContentId from the content creators
        // and return it.
        Set<Long> losers = new HashSet<>();
        for (Content content : this.contents) {
            if (!content.getCreator().getId().equals(winnerContentId)) {
                losers.add(content.getCreator().getId());
            }
        }

        return losers;
    }
}
