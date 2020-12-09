package edu.cnm.deepdive.albuquirky.service;

import edu.cnm.deepdive.albuquirky.model.dao.ProfilePictureRepository;
import edu.cnm.deepdive.albuquirky.model.entity.Profile;
import edu.cnm.deepdive.albuquirky.model.entity.ProfilePicture;
import java.io.IOException;
import java.util.Optional;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.multipart.MultipartFile;

/**
 * This class handles all of the business logic for getting, putting, posting, and deleting items
 * from the {@link ProfilePicture} on behalf of the {@link ProfilePictureService} class, using
 * methods from the {@link ProfilePictureRepository} interface.
 */
@Service
public class ProfilePictureService {

  private static final String UNTITLED_FILENAME = "untitled";

  private final ProfilePictureRepository profilePictureRepository;
//  private final StorageService storageService;

  /**
   * Creates instances of the {@link ProfilePictureRepository}.
   * @param profilePictureRepository The {@link ProfilePictureRepository} to be created.
   */
  public ProfilePictureService(
      ProfilePictureRepository profilePictureRepository) {
    this.profilePictureRepository = profilePictureRepository;
//    this.storageService = storageService;
  }

  /**
   * Retrieves a profile picture by its ID.
   * @param id The ID of the {@link ProfilePicture}.
   * @return The matching {@link ProfilePicture}, if one exists.
   */
  public Optional<ProfilePicture> get(@NonNull long id) {
    return profilePictureRepository.findById(id);
  }

  /**
   * Retrieves a profile picture by its ID and the user.
   * @param id The ID of the {@link ProfilePicture}.
   * @param user The {@link Profile} of the user.
   * @return @return The matching {@link ProfilePicture}, if one exists.
   */
  public Optional<ProfilePicture> get(@NonNull long id, @NonNull Profile user) {
    return profilePictureRepository.findFirstByIdAndUser(id, user);
  }

  /**
   * Deletes a profile picture from the database.
   * @param image The {@link ProfilePicture} to be deleted.
   * @throws IOException If the file cannot be accessed from the reference provided.
   */
  public void delete(@NonNull ProfilePicture image) throws IOException {
//    storageService.delete(image.getPath());
    profilePictureRepository.delete(image);
  }

  /**
   * Updates a {@link ProfilePicture} in the database.
   * @param image The {@link ProfilePicture} to be saved.
   * @return The {@link ProfilePicture} object saved.
   */
  public ProfilePicture save(@NonNull ProfilePicture image) {
    return profilePictureRepository.save(image);
  }

  /**
   * Stores a profile picture in the database.
   * @param file The actual image of the {@link ProfilePicture}.
   * @param user The user {@link Profile}.
   * @return The {@link ProfilePicture} that was stored.
   * @throws IOException If the file cannot be accessed from the reference provided.
   * @throws HttpMediaTypeException If the file is of an extension other than those appearing on
   * the whitelist.
   */
  public ProfilePicture store(@NonNull MultipartFile file, @NonNull Profile user)
      throws IOException, HttpMediaTypeException {
    String originalFilename = file.getOriginalFilename();
    String contentType = file.getContentType();
//    String reference = storageService.store(file);
    ProfilePicture image = new ProfilePicture();
    image.setUser(user);
    image.setName((originalFilename != null) ? originalFilename : UNTITLED_FILENAME);
    image.setContentType(
        (contentType != null) ? contentType : MediaType.APPLICATION_OCTET_STREAM_VALUE);
//    image.setPath(reference);
    return save(image);
  }

  /**
   * Retrieves a profile picture from the database.
   * @param image The {@link ProfilePicture} to retrieve.
   * @return The actual image for the {@link ProfilePicture}.
   * @throws IOException If the file cannot be accessed from the reference provided.
   */
  public Resource retrieve(@NonNull ProfilePicture image) throws IOException {
//    return storageService.retrieve(image.getPath());
    return null;
  }

}
