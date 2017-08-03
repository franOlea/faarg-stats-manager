package org.faarg.stats.manager.team;

import org.faarg.stats.manager.team.player.Player;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "TEAMS")
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY")
    private Category category;

    @Column(name = "COACH_NAME", nullable = false)
    private String coachName;

    @Column(name = "WEBSITE_URL")
    private String websiteUrl;

    @Column(name = "CREATION_YEAR")
    private Date creationYear;

    @OneToMany(mappedBy = "team")
    private Set<Player> players;

    protected Team() {
    }

    public Team(final String name,
                final Category category,
                final String coachName,
                final String websiteUrl,
                final Date creationYear,
                final Set<Player> players) {
        this.name = name;
        this.category = category;
        this.coachName = coachName;
        this.websiteUrl = websiteUrl;
        this.creationYear = creationYear;
        this.players = players;
    }
}
