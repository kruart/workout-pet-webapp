package ua.kruart.workout.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Contains spring configuration for scanning packages and creating beans
 *
 * @author kruart on 24.07.2017.
 */
@Configuration
@ComponentScan(basePackages = "ua.kruart.**.service")
@ImportResource({"classpath:spring/spring-db.xml", "classpath:spring/spring-security.xml"})
public class ApplicationConfig {}