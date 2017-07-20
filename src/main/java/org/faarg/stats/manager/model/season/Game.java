package org.faarg.stats.manager.model.season;

import org.faarg.stats.manager.model.team.Team;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "GAMES")
public class Game {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "LOCAL_TEAM", nullable = false)
    private Team localTeam;

    @Column(name = "VISIT_TEAM", nullable = false)
    private Team visitTeam;

    @OneToMany(mappedBy = "team")
    private List<Play> plays;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="RESULT")
    private Result result;

    public Game() {
    }
}
