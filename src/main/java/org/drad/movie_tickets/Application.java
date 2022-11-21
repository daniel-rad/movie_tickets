package org.drad.movie_tickets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "org.drad")
@EnableJpaRepositories(basePackages = "org.drad.*")
@EntityScan(basePackages = "org.drad.*")
public class Application extends SpringBootServletInitializer {

    private static final Logger log = LogManager.getLogger(Application.class);

    public static void main(final String[] args) {
        log.info("Starting the application");
        final SpringApplication springApplication = new Application()
              .configure(new SpringApplicationBuilder(Application.class))
              .bannerMode(Mode.OFF)
              .build();

        springApplication.run(args);
    }
}
