package org.faarg.stats.manager;

import org.faarg.stats.manager.model.team.Team;
import org.faarg.stats.manager.model.team.player.Position;
import org.faarg.stats.manager.service.repository.PositionRepository;
import org.faarg.stats.manager.service.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;

@SpringBootApplication
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner loadData(PositionRepository positionRepository,
                                      TeamRepository teamRepository) {
        return (args) -> {
            positionRepository.save(new Position("QB"));
            positionRepository.save(new Position("RB"));
            positionRepository.save(new Position("WR"));
            positionRepository.save(new Position("TE"));

            log.info("Positions found with findAll():");
            log.info("-------------------------------");
            for (Position position : positionRepository.findAll()) {
                log.info(position.toString());
            }
            log.info("");

            teamRepository.save(new Team("Sarasa Team", "coach name", "www.sarasa.com", new Date(0)));

            log.info("Teams found with findAll():");
            log.info("-------------------------------");
            for (Team team : teamRepository.findAll()) {
                log.info(team.toString());
            }
            log.info("");
        };
    }

}
