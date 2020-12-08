package edu.cnm.deepdive.albuquirky.configuration;

import java.util.Random;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This configuration class contains {@code JavaBeans} to support the server application.
 */
@Configuration
public class Beans {

  /**
   * This method provides a new instance of {@code Random}.
   * @return A new instance of Random.
   */
  @Bean
  public Random basicRandom() {
    return new Random();
  }

}
