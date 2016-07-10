package com.tirthal.learning.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages={"com.tirthal.learning.repository"})
public class JpaConfiguration {

}
