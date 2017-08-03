package org.faarg.stats.manager.play;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "EVENT_TYPE")
@Table(name = "DRIVE_EVENTS")
public abstract class DriveEvent {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    protected Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DRIVE")
    protected Drive drive;

}
