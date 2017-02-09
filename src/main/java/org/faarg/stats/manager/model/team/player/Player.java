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

    public Player(final String firstName,
                  final String lastName,
                  final Date birthDate,
                  final Date joinedYear,
                  final float weight,
                  final float height,
                  final String profileImageUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.joinedYear = joinedYear;
        this.weight = weight;
        this.height = height;
        this.profileImageUrl = profileImageUrl;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getJoinedYear() {
        return joinedYear;
    }

    public void setJoinedYear(Date joinedYear) {
        this.joinedYear = joinedYear;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}
