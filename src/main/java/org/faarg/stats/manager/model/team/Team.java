package org.faarg.stats.manager.model.team;

import org.faarg.stats.manager.model.team.player.Player;

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

    @ManyToOne(fetch = FetchType.EAGER)
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

    public Team() {
    }

    public Team(final String name,
                final String coachName,
                final String websiteUrl,
                final Date creationYear) {
        this.name = name;
        this.coachName = coachName;
        this.websiteUrl = websiteUrl;
        this.creationYear = creationYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(final String coachName) {
        this.coachName = coachName;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(final String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public Date getCreationYear() {
        return creationYear;
    }

    public void setCreationYear(final Date creationYear) {
        this.creationYear = creationYear;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(final Set<Player> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coachName='" + coachName + '\'' +
                ", websiteUrl='" + websiteUrl + '\'' +
                ", creationYear=" + creationYear +
                ", players=" + players +
                '}';
    }
}
