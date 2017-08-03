package org.faarg.stats.manager.play;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("PENALTY")
@Table(name="PENALTY_EVENTS")
public class PenaltyEvent extends DriveEvent {


    @OneToMany(mappedBy = "penaltyEvent")
    private List<Penalty> penalties;

}
