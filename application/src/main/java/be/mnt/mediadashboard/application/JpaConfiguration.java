package be.mnt.mediadashboard.application;

import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "be.mnt.mediadashboard")
@EntityScan("be.mnt.mediadashboard")
public class JpaConfiguration {
}
