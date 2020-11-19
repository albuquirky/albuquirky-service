package edu.cnm.deepdive.albuquirky.configuration;

import java.util.Random;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

  @Bean
  public Random basicRandom() {
    return new Random();
  }
}
