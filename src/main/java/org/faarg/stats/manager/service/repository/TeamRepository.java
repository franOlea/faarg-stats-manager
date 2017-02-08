package org.faarg.stats.manager.service.repository;

import org.faarg.stats.manager.model.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "teams", path = "teams")
public interface TeamRepository extends JpaRepository<Team, Long> {
}
