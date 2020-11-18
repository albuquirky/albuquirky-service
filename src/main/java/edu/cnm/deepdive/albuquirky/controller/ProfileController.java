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
    return (Profile) auth.getPrincipal();
  }

  @GetMapping(value = "/{userId:[0-9a-fA-F\\-]{32,}}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Profile get(@PathVariable Long userId, Authentication auth) {
    return profileService.getById(userId)
        .orElseThrow(NoSuchElementException::new);
  }

  @PutMapping(value = "/me/image")
  public String uploadImage(@RequestParam MultipartFile file, Authentication auth)
      throws IOException {
    return profileService.uploadFile(file, (Profile) auth.getPrincipal());
  }
}
