package edu.cnm.deepdive.albuquirky.service;

import edu.cnm.deepdive.albuquirky.model.dao.ProfileRepository;
import edu.cnm.deepdive.albuquirky.model.entity.Profile;
import edu.cnm.deepdive.albuquirky.model.entity.ProfilePicture;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * This class handles all of the business logic for getting, putting, posting, and deleting items
 * from the {@link Profile} on behalf of the {@link ProfileService} class, using methods from the
 * {@link ProfileRepository} interface.
 */
@Service
public class ProfileService implements Converter<Jwt, UsernamePasswordAuthenticationToken> {

  private final Path uploadRoot;
  private final Random rng;
  private final ProfileRepository profileRepository;

  /**
   * The constructor initializes a new instance of {@code Random} and a {@link ProfileRepository}.
   * @param rng A new instance of {@code Random}.
   * @param profileRepository The instance of {@link ProfileRepository} to be initialized.
   * @param applicationHome
   */
  @Autowired
  public ProfileService(Random rng, ProfileRepository profileRepository,
      ApplicationHome applicationHome) {
    this.rng = rng;
    this.profileRepository = profileRepository;
    uploadRoot = applicationHome.getDir().toPath().resolve("uploads");
    uploadRoot.toFile().mkdirs();
  }

  /**
   * Method to get the user {@link Profile}, or create it if it does not already exist.
   * @param oauth The user OAuth key.
   * @param username The username associated with the user {@link Profile}.
   * @param email The user's email address.
   * @return The {@link Profile} retrieved or created.
   */
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

  /**
   * Updates a {@link Profile} in the database.
   * @param profile The {@link Profile} to be updated.
   * @return The updated {@link Profile}.
   */
  public Profile save(Profile profile) {
    return profileRepository.save(profile);
  }

  /**
   *
   * @param jwt
   * @return
   */
  @Override
  public UsernamePasswordAuthenticationToken convert(Jwt jwt) {
    Collection<SimpleGrantedAuthority> grants =
        Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    return new UsernamePasswordAuthenticationToken(
        getOrCreate(jwt.getSubject(), jwt.getClaim("name"),
            jwt.getClaim("email")), jwt.getTokenValue(), grants);
  }

  /**
   * Retrieves a {@link Profile} by their ID.
   * @param id The ID to be searched.
   * @return An {@link Optional} intended to contain the matching {@link Profile}.
   */
  public Optional<Profile> getById(Long id) {
    return profileRepository.findById(id);
  }

  /**
   * Retrieves a {@link Profile} by their username.
   * @param name The username to be searched.
   * @return An {@link Optional} intended to contain the matching {@link Profile}.
   */
  public Optional<Profile> findByUsername(String name) {
    return profileRepository.findFirstByUsername(name);
  }

  /**
   * Retrieves a {@link Profile} by their authorization.
   * @param auth The authorization to be searched.
   * @return An {@link Optional} intended to contain the matching {@link Profile}.
   */
  public Optional<Profile> findByAuth(String auth) {
    return profileRepository.findFirstByOauth(auth);
  }

  /**
   * Uploads a file to the database.
   * @param file The file to be uploaded.
   * @param profile The {@link Profile} the file will be attached to.
   * @return
   * @throws IOException
   */
  public Profile uploadFile(MultipartFile file, Profile profile) throws IOException {
    ProfilePicture image = (profile.getImage() != null) ? profile.getImage() : new ProfilePicture();
    String fileExtension = getFileExtension(file);
    String fileName = getRandomString();
    String newFileName = String.format("%s%s", fileName, fileExtension);
    Path resolvedPath = uploadRoot.resolve(newFileName);
    Files.copy(file.getInputStream(), resolvedPath);
    image.setPath(newFileName);
    image.setUser(profile);
    image.setName(file.getName());
    image.setContentType(
        (file.getContentType() != null)
            ? file.getContentType()
            : MediaType.APPLICATION_OCTET_STREAM_VALUE);
    profile.setImage(image);
    return profileRepository.save(profile);
  }

  public Resource retrieve(Profile profile) throws IOException {
    return Optional.ofNullable(profile.getImage())
        .map((image) -> uploadRoot.resolve(image.getPath()))
        .map((path) -> {
          try {
            return new UrlResource(path.toUri());
          } catch (MalformedURLException e) {
            return null;
          }
        })
        .orElseThrow(IOException::new);
  }

  private String getFileExtension(MultipartFile file) {
    String fileName = file.getOriginalFilename();
    return fileName.substring(fileName.lastIndexOf('.'));
  }

  private String getRandomString() {
    return String.format("%d-%d", System.currentTimeMillis(), rng.nextInt(1_000_000));
  }

}
