package org.faarg.stats.manager.team.player;

import org.faarg.stats.manager.team.player.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "positions", path = "positions")
public interface PositionRepository extends JpaRepository<Position, Long> {

    @RestResource(path = "by-name", rel = "by-name")
    Position findByName(String name);

}