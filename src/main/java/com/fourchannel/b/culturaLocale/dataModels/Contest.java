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
    @ManyToOne
    private TownHall townHall;
    @ManyToOne
    private User creator;

    /**
     * Constructs a Contest entity based on a {@link ContestCreationRequestDTO}, initializing it with provided contest details
     * and setting it as open. This constructor is designed to facilitate the creation of a Contest entity directly from a
     * data transfer object (DTO).
     *
     * @param dto The {@link ContestCreationRequestDTO} containing contest creation details.
     */
    public Contest(ContestCreationRequestDTO dto) {
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.initialDate = dto.getInitialDate();
        this.endDate = dto.getEndDate();
        this.rules = dto.getRules();
        this.type = dto.getType();
        this.contents = new ArrayList<>();
        this.contestOpen = true;

        // set it to fill it later
        this.townHall = new TownHall();
        this.townHall.setId(dto.getTownHallId());

        // set it to fill it later
        this.creator = new User();
        this.creator.setId(dto.getCreatorId());
    }

    /**
     * Subscribes a {@link Content} to this contest. Ensures that the content is not already subscribed to avoid duplicates.
     * Throws IllegalStateException if the content is already part of the contest.
     *
     * @param content The {@link Content} to be subscribed to the contest.
     * @throws IllegalStateException If the content is already part of the contest.
     */
    public void subscribe(Content content) {
        // make sure it's not here already
        for (Content c : contents) {
            if (c.getId().equals(content.getId())) {
                throw new IllegalStateException("Content already subscribed to contest.");
            }
        }

        this.contents.add(content);
    }

    /**
     * Closes the contest, marks a specific {@link Content} as the winning content, and determines the set of user IDs who
     * did not win. Throws IllegalStateException if the contest is already closed or if the specified winning content does not
     * exist within the contest.
     *
     * @param winnerContentId The ID of the winning {@link Content}.
     * @return A {@link Set} of user IDs who did not win the contest.
     * @throws IllegalStateException If the contest is already closed or if the winning content is not part of the contest.
     */
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
