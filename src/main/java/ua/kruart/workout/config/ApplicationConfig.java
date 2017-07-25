package ua.kruart.workout.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Contains spring configuration for scanning packages and creating beans
 *
 * @author kruart on 24.07.2017.
 */
@Configuration
@ComponentScan(basePackages = "ua.kruart.**.service")
public class ApplicationConfig {}