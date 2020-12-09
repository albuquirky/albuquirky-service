package edu.cnm.deepdive.albuquirky.controller;

import edu.cnm.deepdive.albuquirky.model.entity.Image;
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

/**
 * The ImageController class is the @RestController that maps the endpoints of "/images" for the
 * communication between the server-side and client-side for {@link ProfilePicture}.
 */
@RestController
@RequestMapping("/profile_pictures")
@ExposesResourceFor(ProfilePicture.class)
public class ProfilePictureController {

  private final ProfileService profileService;
  private final ProfilePictureService profilePictureService;

  /**
   * The constructor initializes an instance of {@link ProfileService} and
   * {@link ProfilePictureService}.
   * @param profileService The {@link ProfileService} to initialize.
   * @param profilePictureService The {@link ProfilePictureService} to initialize.
   */
  @Autowired
  public ProfilePictureController(
      ProfileService profileService, ProfilePictureService profilePictureService) {
    this.profileService = profileService;
    this.profilePictureService = profilePictureService;
  }

  /**
   * The Get method which returns a {@link ProfilePicture} for a specified user.
   * @param id The ID of the {@link Profile}.
   * @param auth The user authentication.
   * @return The matching {@link ProfilePicture}.
   */
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
