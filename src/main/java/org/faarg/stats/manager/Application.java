package org.faarg.stats.manager;

import org.faarg.stats.manager.model.league.team.player.Position;
import org.faarg.stats.manager.service.repository.PositionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner loadData(PositionRepository repository) {
        return (args) -> {
            repository.save(new Position("QB"));
            repository.save(new Position("RB"));
            repository.save(new Position("WR"));
            repository.save(new Position("TE"));

            log.info("Positions found with findAll():");
            log.info("-------------------------------");
            for (Position position : repository.findAll()) {
                log.info(position.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            Position position = repository.findOne(1L);
            log.info("Position found with findOne(1L):");
            log.info("--------------------------------");
            log.info(position.toString());
            log.info("");

            // fetch customers by last name
            log.info("Position found with findByNameIgnoreCare('QB'):");
            log.info("--------------------------------------------");
            Position quarterBack = repository.findByName("QB");
            log.info(quarterBack.toString());

            log.info("");
        };
    }

}
