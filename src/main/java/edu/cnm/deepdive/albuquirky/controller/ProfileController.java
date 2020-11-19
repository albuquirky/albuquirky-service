package edu.cnm.deepdive.albuquirky.controller;

import edu.cnm.deepdive.albuquirky.model.entity.Profile;
import edu.cnm.deepdive.albuquirky.service.ProfileService;
import java.io.IOException;
import java.util.NoSuchElementException;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/profiles")
@ExposesResourceFor(Profile.class)
public class ProfileController {

  private final ProfileService profileService;

  public ProfileController(ProfileService profileService) {
    this.profileService = profileService;
  }

  @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
  public Profile me(Authentication auth) {
    return getAuthProfile(auth);
  }

  @GetMapping(value = "/{userId:[0-9a-fA-F\\-]{32,}}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Profile getOrder(@PathVariable Long userId, Authentication auth) {
    return profileService.getById(userId)
        .orElseThrow(NoSuchElementException::new);
  }

  @GetMapping(value = "/me/username", produces = MediaType.APPLICATION_JSON_VALUE)
  public String getUsername(Authentication auth) {
    return getAuthProfile(auth).getUsername();
  }

  @PutMapping(value = "/me/username", consumes = MediaType.APPLICATION_JSON_VALUE)
  public String updateUsername(@RequestParam String name, Authentication auth) {
    Profile profile = getAuthProfile(auth);
    profile.setUsername(name);
    return profileService.save(profile).getUsername();
  }

  @GetMapping(value = "/me/image", produces = MediaType.APPLICATION_JSON_VALUE)
  public String getImage(Authentication auth) {
    return getAuthProfile(auth).getImage();
  }

  @PutMapping(value = "/me/image", consumes = MediaType.APPLICATION_JSON_VALUE)
  public String uploadImage(@RequestParam MultipartFile file, Authentication auth)
      throws IOException {
    return profileService.uploadFile(file, getAuthProfile(auth));
  }

  @GetMapping(value = "/me/address", produces = MediaType.APPLICATION_JSON_VALUE)
  public String getAddress(Authentication auth) {
    return getAuthProfile(auth).getAddress();
  }

  @PutMapping(value = "/me/address", consumes = MediaType.APPLICATION_JSON_VALUE)
  public String updateAddress(@RequestParam String address, Authentication auth) {
    Profile profile = getAuthProfile(auth);
    profile.setAddress(address);
    return profileService.save(profile).getAddress();
  }

  private static Profile getAuthProfile(Authentication auth) {
    return (Profile) auth.getPrincipal();
  }

}
