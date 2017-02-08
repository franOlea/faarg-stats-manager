package org.faarg.stats.manager.model.league.team;

import org.faarg.stats.manager.model.league.team.player.Player;

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

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "COACH_NAME", nullable = false)
    private String coachName;

    @Column(name = "WEBSITE_URL")
    private String websiteUrl;

    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @OneToMany(mappedBy = "team")
    private Set<Player> players;

    public Team() {
    }

}
