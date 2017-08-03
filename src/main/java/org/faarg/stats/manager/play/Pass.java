package org.faarg.stats.manager.play;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("PASS_PLAY")
@Table(name="PASS_PLAYS")
public class Pass extends Play {



}
