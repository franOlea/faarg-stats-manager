package org.faarg.stats.manager.model.team.player;

import org.faarg.stats.manager.model.team.Team;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "PLAYERS")
public class Player {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "BIRTH_DATE")
    private Date birthDate;

    @Column(name = "JOINED_YEAR")
    private Date joinedYear;

    @Column(name = "WEIGHT")
    private float weight;

    @Column(name = "HEIGHT")
    private float height;

    @ManyToMany
    @JoinTable(
            name = "PLAYERS_POSITIONS",
            joinColumns = @JoinColumn(name = "PLAYER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "POSITION_ID", referencedColumnName = "ID")
    )
    private Set<Position> positions;

    @Column(name = "PROFILE_IMAGE_URL")
    private String profileImageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM")
    private Team team;

    public Player() {
    }
}
