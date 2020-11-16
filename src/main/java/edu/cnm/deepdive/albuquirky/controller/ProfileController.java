package edu.cnm.deepdive.albuquirky.controller;

import edu.cnm.deepdive.albuquirky.service.ProfileService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

  private final ProfileService profileService;

  public ProfileController(ProfileService profileService) {
    this.profileService = profileService;
  }
}
