package edu.cnm.deepdive.albuquirky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;

/**
 * The initialization of Springboot server for AlbuQuirky application.
 *
 * @author Tyler Baum
 * @author Ricky Garcia
 * @author John Jaramillo
 * @author Justin Kelly
 *
 * @version 1.0
 * @since 1.0
 *
 */
@EnableHypermediaSupport(type = {HypermediaType.HAL})
@SpringBootApplication
public class AlbuquirkyApplication {

  /**
   * Construct
   * @param
   */
  public static void main(String[] args) {
    SpringApplication.run(AlbuquirkyApplication.class, args);
  }

}
