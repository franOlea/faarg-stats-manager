package org.faarg.stats.manager.game;

import javax.persistence.*;

@Entity
@Table(name = "GAME_RESULTS")
public class Result {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "Q1_LOCAL", nullable = false)
    private Integer firstQuarterLocalTeam;
    
    @Column(name = "Q2_LOCAL", nullable = false)
    private Integer secondQuarterLocalTeam;
    
    @Column(name = "Q3_LOCAL")
    private Integer thirdQuarterLocalTeam;
    
    @Column(name = "Q4_LOCAL")
    private Integer fourthQuarterLocalTeam;

    @Column(name = "Q1_VISIT", nullable = false)
    private Integer firstQuarterVisitTeam;

    @Column(name = "Q2_VISIT", nullable = false)
    private Integer secondQuarterVisitTeam;

    @Column(name = "Q3_VISIT")
    private Integer thirdQuarterVisitTeam;

    @Column(name = "Q4_VISIT")
    private Integer fourthQuarterVisitTeam;

    public Result() {
    }
}
