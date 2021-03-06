package edu.cnm.deepdive.albuquirky.configuration;

import edu.cnm.deepdive.albuquirky.service.ProfileService;
import java.beans.BeanProperty;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.jwt.JwtClaimValidator;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

/**
 * The SecurityConfiguration class handles the security for the AlbuQuirky web server application
 * by checking the credentials for the web server match the Google Cloud credentials for the
 * project.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final ProfileService profileService;

  @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
  private String issuerUri;

  @Value("${spring.security.oauth2.resourceserver.jwt.client-id}")
  private String clientId;

  /**
   * Constructor for the {@code SecurityConfiguration} class. It sets the reference to the
   * {@code ProfileService} for the field.
   *
   * @param profileService An instance of {@link ProfileService}.
   */
  @Autowired
  public SecurityConfiguration(ProfileService profileService) {
    this.profileService = profileService;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        // Add any endpoint-specific access rules. Order-dependent.
        .authorizeRequests((auth) -> auth.anyRequest().authenticated())
        .oauth2ResourceServer().jwt()
        .jwtAuthenticationConverter(profileService);
  }

  /**
   * This method validates the JWT token received from the Google Cloud service, and returns a
   * {@code JwtDecoder} object.
   *
   * @return The {@code JWT} token decoder.
   */
  @BeanProperty
  public JwtDecoder jwtDecoder(){
    NimbusJwtDecoder decoder = (NimbusJwtDecoder) JwtDecoders.fromIssuerLocation(issuerUri);
    OAuth2TokenValidator<Jwt> audienceValidator =
        new JwtClaimValidator<List<String>>(JwtClaimNames.AUD, (aud) -> aud.contains(clientId));
    OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuerUri);
    OAuth2TokenValidator<Jwt> combinedValidator =
        new DelegatingOAuth2TokenValidator<Jwt>(withIssuer, audienceValidator);
    decoder.setJwtValidator(combinedValidator);
    return decoder;
  }

}
