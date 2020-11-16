package edu.cnm.deepdive.albuquirky.configuration;

import edu.cnm.deepdive.albuquirky.service.ProfileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final ProfileService profileService;

  @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
  private String issuerUri;

  @Value("${spring.security.oauth2.resourceserver.jwt.client-id}")
  private String clientId;

  public SecurityConfiguration(ProfileService profileService) {
    this.profileService = profileService;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests((auth) -> auth.anyRequest().authenticated())
        .oauth2ResourceServer().jwt()
        .jwtAuthenticationConverter(profileService);
  }

}
