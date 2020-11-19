package edu.cnm.deepdive.albuquirky.service;

import edu.cnm.deepdive.albuquirky.model.dao.ProfileRepository;
import edu.cnm.deepdive.albuquirky.model.entity.Profile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProfileService implements Converter<Jwt, UsernamePasswordAuthenticationToken> {

  private final Path uploadRoot;
  private final Random rng;
  private final ProfileRepository profileRepository;

  @Autowired
  public ProfileService(Random rng, ProfileRepository profileRepository) {
    this.rng = rng;
    this.profileRepository = profileRepository;
    uploadRoot = Paths.get("uploads");
  }

  public Profile getOrCreate(String oauth, String username, String email) {
    return profileRepository.findFirstByOauth(oauth)
        .orElseGet(() -> {
          Profile profile = new Profile();
          profile.setOauth(oauth);
          profile.setUsername(username);
          profile.setEmail(email);
          return profileRepository.save(profile);
        });
  }

  public Profile save(Profile profile) {
    return profileRepository.save(profile);
  }

  @Override
  public UsernamePasswordAuthenticationToken convert(Jwt jwt) {
    Collection<SimpleGrantedAuthority> grants =
        Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    return new UsernamePasswordAuthenticationToken(
        getOrCreate(jwt.getSubject(), jwt.getClaim("name"),
            jwt.getClaim("email")), jwt.getTokenValue(), grants);
  }

  public Optional<Profile> findFirstByUsername(String name) {
    return profileRepository.findFirstByUsername(name);
  }

  public Optional<Profile> findFirstByOauth(String oauth) {
    return profileRepository.findFirstByOauth(oauth);
  }

  public Optional<Profile> getById(Long id) {
    return profileRepository.findById(id);
  }

  public Optional<Profile> findByUsername(String name) {
    return profileRepository.findFirstByUsername(name);
  }

  public Optional<Profile> findByAuth(String auth) {
    return profileRepository.findFirstByOauth(auth);
  }

  public String uploadFile(MultipartFile file, Profile profile) throws IOException {
    String fileExtension = getFileExtension(file);
    String fileName = getRandomString();
    String newFileName = fileName + fileExtension;
    Files.copy(file.getInputStream(), uploadRoot.resolve(newFileName));
    profile.setImage(newFileName);
    profileRepository.save(profile);
    return newFileName;
  }

  private String getFileExtension(MultipartFile file) {
    String fileName = file.getOriginalFilename();
    return fileName.substring(fileName.lastIndexOf('.'));
  }

  private String getRandomString() {
    return String.format("%d-%d", System.currentTimeMillis(), rng.nextInt(1_000_000));
  }

}
