package org.faarg.stats.manager.game;

import org.faarg.stats.manager.play.Drive;
import org.faarg.stats.manager.team.Team;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "GAMES")
public class Game {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LOCAL_TEAM", nullable = false)
    private Team localTeam;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "VISIT_TEAM", nullable = false)
    private Team visitTeam;

    @OneToMany(mappedBy = "game")
    private List<Drive> drives;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="RESULT")
    private Result result;

    protected Game() {
    }

}
