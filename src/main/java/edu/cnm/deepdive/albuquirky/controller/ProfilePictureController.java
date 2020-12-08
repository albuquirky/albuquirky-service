package edu.cnm.deepdive.albuquirky.controller;

import edu.cnm.deepdive.albuquirky.model.entity.Profile;
import edu.cnm.deepdive.albuquirky.model.entity.ProfilePicture;
import edu.cnm.deepdive.albuquirky.service.ProfilePictureService;
import edu.cnm.deepdive.albuquirky.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile_pictures")
@ExposesResourceFor(ProfilePicture.class)
public class ProfilePictureController {

  private final ProfileService profileService;
  private final ProfilePictureService profilePictureService;

  @Autowired
  public ProfilePictureController(
      ProfileService profileService, ProfilePictureService profilePictureService) {
    this.profileService = profileService;
    this.profilePictureService = profilePictureService;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ProfilePicture getUserProfilePicture(@PathVariable long id, Authentication auth) {
    return profilePictureService.get(id, getAuthProfile(auth))
        .orElseThrow(/* TODO Write custom exception? */);
  }

  // TODO Update image

  // TODO Post new
  // These two are intrinsically tied together - when a post is made, a delete happens
  // immediately before or after to delete the old file.
  // TODO Delete old

  private static Profile getAuthProfile(Authentication auth) {
    return (Profile) auth.getPrincipal();
  }

}
