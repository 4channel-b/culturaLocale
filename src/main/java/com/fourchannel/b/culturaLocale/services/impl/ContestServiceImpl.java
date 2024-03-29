package com.fourchannel.b.culturaLocale.services.impl;

import com.fourchannel.b.culturaLocale.dataModels.Content;
import com.fourchannel.b.culturaLocale.dataModels.Contest;
import com.fourchannel.b.culturaLocale.dataModels.Notification;
import com.fourchannel.b.culturaLocale.dataModels.users.Role;
import com.fourchannel.b.culturaLocale.dataModels.users.User;
import com.fourchannel.b.culturaLocale.repositories.ContentRepository;
import com.fourchannel.b.culturaLocale.repositories.ContestRepository;
import com.fourchannel.b.culturaLocale.repositories.NotificationRepository;
import com.fourchannel.b.culturaLocale.repositories.UserRepository;
import com.fourchannel.b.culturaLocale.services.ContestService;
import com.fourchannel.b.culturaLocale.services.TownHallRoleService;
import com.fourchannel.b.culturaLocale.services.TownHallService;
import com.fourchannel.b.culturaLocale.services.UserService;
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
    private final TownHallRoleService townHallRoleService;
    private final TownHallService townHallService;
    private final UserService userService;

    public ContestServiceImpl(ContestRepository contestRepository,
                              ContentRepository contentRepository,
                              UserRepository userRepository,
                              NotificationRepository notificationRepository,
                              TownHallRoleService townHallRoleService,
                              TownHallService townHallService,
                              UserService userService)
    {
        this.contestRepository = contestRepository;
        this.contentRepository = contentRepository;
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
        this.townHallRoleService = townHallRoleService;
        this.townHallService = townHallService;
        this.userService = userService;
    }

    /**
     * Creates a new contest with specified contents. Validates the creator's role as Animator within the town hall.
     * Throws IllegalArgumentException if the contest is null or the creator is not an Animator. All contents must exist.
     *
     * @param contest The contest to be created.
     * @param contents A list of content IDs to include in the contest.
     * @return The created and saved contest.
     * @throws IllegalArgumentException if contest is null, creator is not Animator, or content does not exist.
     */
    @Override
    public Contest createContest(Contest contest, List<Long> contents)
    {
        if (contest == null)
        {
            throw new IllegalArgumentException("| ERROR | Contest is NULL");
        }

        if (townHallRoleService.getRole(contest.getCreator().getId(), contest.getTownHall().getId())
                != Role.Animator) {
            throw new IllegalStateException("| ERROR | You need to be an animator to do this.");
        }

        // fill in partially filled data points
        contest.setTownHall(townHallService.getById
                (contest.getTownHall().getId()));
        contest.setCreator(userService.getUser(contest.getCreator().getId()));

        // subscribe all passed contents to the contest!
        contents.forEach(c -> {
            contest.subscribe(contentRepository.findById(c)
                    .orElseThrow(() -> new IllegalArgumentException("| ERROR | Cannot add contents that don't exist!")));
        });

        return contestRepository.save(contest);
    }

    /**
     * Retrieves a contest by its ID. Returns null if no contest is found.
     *
     * @param id The ID of the contest to retrieve.
     * @return The contest if found, null otherwise.
     */
    public Contest getContest(Long id)
    {
        return this.contestRepository.findById(id).orElse(null);
    }

    /**
     * Retrieves all contests available in the system.
     *
     * @return A list of all contests.
     */
    @Override
    public List<Contest> getAllContest()
    {
        return StreamSupport.stream(contestRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    /**
     * Updates a contest with new contents. Validates the existence of the contest and all new contents.
     * Throws IllegalArgumentException if the contest is null, does not exist, or if any content does not exist.
     *
     * @param contest The contest to update.
     * @param contents A list of content IDs to update the contest with.
     * @throws IllegalArgumentException if contest is null, does not exist, or content does not exist.
     */
    @Override
    public void updateContest(Contest contest, List<Long> contents)
    {
        if (contest == null) {
            throw new IllegalArgumentException("| ERROR | Contest is NULL");
        }

        Contest original = contestRepository.findById(contest.getId())
                .orElseThrow(() -> new IllegalArgumentException("| ERROR | Contest doesn't exist"));

        for (Long id : contents)
        {
            Content elem = this.contentRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("| ERROR | Content doesn't exist"));
            contest.getContents().add(elem);
        }

        contest.setTownHall(original.getTownHall());
        contest.setCreator(original.getCreator());

        contestRepository.save(contest);
    }

    /**
     * Subscribes a content to a contest. Validates both the contest and content existence.
     * Throws IllegalArgumentException if either does not exist.
     *
     * @param contentId The ID of the content to subscribe.
     * @param contestId The ID of the contest to which the content is subscribing.
     * @throws IllegalArgumentException if the contest or content does not exist.
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
     * Terminates a contest by selecting a winning content. Notifies all participants of the result.
     * Throws IllegalArgumentException if the contest or winning content does not exist.
     *
     * @param contestId The ID of the contest to terminate.
     * @param winningContentId The ID of the winning content.
     * @throws IllegalArgumentException if the contest or content does not exist.
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
