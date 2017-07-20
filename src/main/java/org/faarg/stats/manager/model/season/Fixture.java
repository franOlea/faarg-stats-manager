package org.faarg.stats.manager.model.season;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Fixture {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;



}
