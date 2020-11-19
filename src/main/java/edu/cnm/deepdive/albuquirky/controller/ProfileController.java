package edu.cnm.deepdive.albuquirky.controller;

import edu.cnm.deepdive.albuquirky.model.entity.Profile;
import edu.cnm.deepdive.albuquirky.service.ProfileService;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO doc
 */
@RestController
@RequestMapping("/profiles")
@ExposesResourceFor(Profile.class)
public class ProfileController {

  private final ProfileService profileService;

  /**
   * TODO doc
   * @param profileService
   */
  public ProfileController(ProfileService profileService) {
    this.profileService = profileService;
  }

  /**
   * TODO doc
   * @param auth
   * @return
   */
  @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
  public Profile me(Authentication auth) {
    return (Profile) auth.getPrincipal();
  }

}
