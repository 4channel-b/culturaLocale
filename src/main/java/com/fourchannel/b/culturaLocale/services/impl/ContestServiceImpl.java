package com.fourchannel.b.culturaLocale.services.impl;

import com.fourchannel.b.culturaLocale.dataModels.Content;
import com.fourchannel.b.culturaLocale.dataModels.Contest;
import com.fourchannel.b.culturaLocale.dataModels.Notification;
import com.fourchannel.b.culturaLocale.dataModels.users.User;
import com.fourchannel.b.culturaLocale.repositories.ContentRepository;
import com.fourchannel.b.culturaLocale.repositories.ContestRepository;
import com.fourchannel.b.culturaLocale.repositories.NotificationRepository;
import com.fourchannel.b.culturaLocale.repositories.UserRepository;
import com.fourchannel.b.culturaLocale.services.ContestService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ContestServiceImpl implements ContestService {
    private final ContestRepository contestRepository;
    private final ContentRepository contentRepository;
    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;
    public ContestServiceImpl(ContestRepository contestRepository,
                              ContentRepository contentRepository,
                              UserRepository userRepository,
                              NotificationRepository notificationRepository)
    {
        this.contestRepository = contestRepository;
        this.contentRepository = contentRepository;
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
    }
    @Override
    public Contest createContest(Contest contest, List<Long> contents)
    {
        if(contest == null)
        {
            throw new IllegalArgumentException("| ERROR | Contest is NULL");
        }

        // subscribe all passed contents to the contest!
        contents.forEach(c -> {
            contest.subscribe(contentRepository.findById(c)
                    .orElseThrow(() -> new IllegalArgumentException("| ERROR | Cannot add contents that don't exist!")));
        });

        return contestRepository.save(contest);
    }

    public Contest getContest(Long id)
    {
        return this.contestRepository.findById(id).orElse(null);
    }

    @Override
    public List<Contest> getAllContest()
    {
        return StreamSupport.stream(contestRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public void updateContest(Contest contest, List<Long> contents)
    {
        if (contest == null) {
            throw new IllegalArgumentException("| ERROR | Contest is NULL");
        }

        contestRepository.findById(contest.getId())
                .orElseThrow(() -> new IllegalArgumentException("| ERROR | Contest doesn't exist"));


        for (Long id : contents)
        {
            Content elem = this.contentRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("| ERROR | Content doesn't exist"));
            contest.getContents().add(elem);
        }

        contestRepository.save(contest);
    }

    /**
     * @param contentId
     * @param contestId
     */
    @Override
    public void subscribeContent(Long contentId, Long contestId) {

        Contest contest = contestRepository.findById(contestId)
                .orElseThrow(() -> new IllegalArgumentException("| ERROR | Contest doesn't exist"));

        Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new IllegalArgumentException("| ERROR | Content doesn't exist"));

        contest.subscribe(content);
        contestRepository.save(contest);
    }

    private Notification buildDefaultLosingNotification(String username, String contestName) {
        Notification notification = new Notification(String.format("Sorry %s!", username),
                String.format("Unfortunately, you did not win our %s contest.",
                        contestName));

        return notification;
    }

    private Notification buildDefaultWinningNotification(String username, String contestName) {
        Notification notification = new Notification(String.format("Congratulations %s!", username),
                String.format("You won our %s contest!", contestName));

        return notification;
    }

    /**
     * @param contestId
     * @param winningContentId
     */
    @Override
    public void terminateContest(Long contestId, Long winningContentId) {
        Contest contest = contestRepository.findById(contestId)
                .orElseThrow(() -> new IllegalArgumentException("| ERROR | Contest doesn't exist"));

        Content winningContent = contentRepository.findById(winningContentId)
                .orElseThrow(() -> new IllegalArgumentException("| ERROR | Content doesn't exist"));

        // get the list of losing users
        Set<Long> losers = contest.closeContest(winningContentId);

        // save the contest status
        contestRepository.save(contest);

        // if the winner had several entries and one of them
        // lost, they should not receive a losing notification
        losers.remove(winningContent.getCreator().getId());

        // build and send out all notifications to users
        losers.forEach(loserId -> {
            User user = userRepository.findById(loserId)
                    .orElseThrow(() -> new IllegalArgumentException("| ERROR | User doesn't exist"));

            Notification notification = buildDefaultLosingNotification(user.getUsername(), contest.getName());
            notification = notificationRepository.save(notification);

            user.addNotification(notification);

            userRepository.save(user);
        });

        // notify the winner!
        User winner = winningContent.getCreator();
        Notification notification = buildDefaultWinningNotification(winner.getUsername(), contest.getName());
        notification = notificationRepository.save(notification);

        winner.addNotification(notification);
        userRepository.save(winner);
    }
}
