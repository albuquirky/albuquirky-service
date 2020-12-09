package edu.cnm.deepdive.albuquirky.controller;

import edu.cnm.deepdive.albuquirky.model.entity.Profile;
import edu.cnm.deepdive.albuquirky.model.entity.ProfilePicture;
import edu.cnm.deepdive.albuquirky.service.ProfileService;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.core.io.Resource;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * The ProfileController class is the @RestController that maps the endpoints of "/profiles" for
 * communication between the server-side and client-side for {@link Profile}
 */
@RestController
@RequestMapping("/profiles")
@ExposesResourceFor(Profile.class)
public class ProfileController {

  private final ProfileService profileService;

  /**
   * Constructs the instance of ProfileService object.
   * @param profileService The {@link ProfileService} to be created.
   */
  public ProfileController(ProfileService profileService) {
    this.profileService = profileService;
  }

  /**
   * The Get method which returns the current authenticated profile.
   * @param auth The user authentication.
   */
  @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
  public Profile me(Authentication auth) {
    return getAuthProfile(auth);
  }

  /**
   * The Get method which returns the {@link Profile} matching the ID provided.
   * @param userId The ID of the {@link Profile} requested.
   * @param auth The user authentication.
   * @return The {@link Profile} matching the ID provided.
   */
  @GetMapping(value = "/{userId:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Profile getOrder(@PathVariable Long userId, Authentication auth) {
    return profileService.getById(userId)
        .orElseThrow(NoSuchElementException::new);
  }

  /**
   * The Get method which returns the username for the current user.
   * @param auth The user authentication.
   * @return The current user's username.
   */
  @GetMapping(value = "/me/username", produces = MediaType.APPLICATION_JSON_VALUE)
  public String getUsername(Authentication auth) {
    return getAuthProfile(auth).getUsername();
  }

  /**
   * The Put method which allows the profile to update their username
   * @param name The new username.
   * @param auth The user authentication.
   * @return The updated username.
   */
  @PutMapping(value = "/me/username",
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
  public String updateUsername(@RequestBody String name, Authentication auth) {
    Profile profile = getAuthProfile(auth);
    profile.setUsername(name);
    return profileService.save(profile).getUsername();
  }

  /**
   * The Get method which returns the profile image.
   * @param auth The user authentication.
   * @return The user's current profile image.
   */
  @GetMapping(value = "/me/image")
  public ResponseEntity<Resource> getImage(Authentication auth) throws IOException {
    Profile profile = getAuthProfile(auth);
    ProfilePicture image = profile.getImage();
    if (image != null) {
      Resource file = profileService.retrieve(profile);
      return ResponseEntity.ok()
          .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", image.getName()))
          .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.contentLength()))
          .header(HttpHeaders.CONTENT_TYPE, image.getContentType())
          .body(file);
    } else {
      throw new NoSuchElementException();
    }
  }

  /**
   * The Put method which updates the current user's profile image.
   * @param file The path to the new image.
   * @param auth The user authentication.
   * @return The updated profile image.
   * @throws IOException
   */
  @PutMapping(value = "/me/image", produces = {MediaType.APPLICATION_JSON_VALUE})
  public Profile uploadImage(@RequestBody MultipartFile file, Authentication auth)
      throws IOException {
    return profileService.uploadFile(file, getAuthProfile(auth));
  }

  /**
   * The Get method which returns the profile address.
   * @param auth The user authentication.
   * @return The user's current address.
   */
  @GetMapping(value = "/me/address", produces = MediaType.APPLICATION_JSON_VALUE)
  public String getAddress(Authentication auth) {
    return getAuthProfile(auth).getAddress();
  }

  /**
   * The Put method which lets the current user update their address.
   * @param address The new user address.
   * @param auth The user authentication.
   * @return The updated user address.
   */
  @PutMapping(value = "/me/address",
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
  public String updateAddress(@RequestBody String address, Authentication auth) {
    Profile profile = getAuthProfile(auth);
    profile.setAddress(address);
    return profileService.save(profile).getAddress();
  }

  private static Profile getAuthProfile(Authentication auth) {
    return (Profile) auth.getPrincipal();
  }

}
