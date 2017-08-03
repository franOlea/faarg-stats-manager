package org.faarg.stats.manager.play;

import org.faarg.stats.manager.team.player.Player;

import javax.persistence.*;

@MappedSuperclass
public abstract class Play extends DriveEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    protected Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PLAYER")
    protected Player player;

    @Column(name = "DOWN_NUMBER", nullable = false)
    protected Integer down;

    @Column(name = "YARD_LINE", nullable = false)
    protected Integer yardLine;

    @Column(name = "OWN_FIELD_SIDE", nullable = false)
    protected Boolean ownSide;

}
