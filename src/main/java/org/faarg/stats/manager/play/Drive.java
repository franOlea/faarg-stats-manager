package org.faarg.stats.manager.play;

import org.faarg.stats.manager.game.Game;
import org.faarg.stats.manager.team.Team;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "DRIVES")
public class Drive {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GAME")
    private Game game;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POSSESSION_TEAM")
    private Team possessionTeam;

    @OneToMany(mappedBy = "drive")
    private List<DriveEvent> driveEvents;

}
