package org.faarg.stats.manager.service.repository;

import org.faarg.stats.manager.model.team.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "players", path = "players")
public interface PlayerRepository extends JpaRepository<Player, Long> {


    @RestResource(path = "by-team-name", rel = "by-team-name")
    List<Player> findByTeamName(String name);

}
