package org.faarg.stats.manager.play;

import javax.persistence.*;

@Entity
@Table(name = "PENALTIES")
public class Penalty {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    protected Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PENALTY_EVENT")
    private PenaltyEvent penaltyEvent;

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

}
