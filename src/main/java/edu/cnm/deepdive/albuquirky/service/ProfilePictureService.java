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

@Service
public class ProfilePictureService {

  private static final String UNTITLED_FILENAME = "untitled";

  private final ProfilePictureRepository profilePictureRepository;
//  private final StorageService storageService;

  public ProfilePictureService(
      ProfilePictureRepository profilePictureRepository) {
    this.profilePictureRepository = profilePictureRepository;
//    this.storageService = storageService;
  }

  public Optional<ProfilePicture> get(@NonNull long id) {
    return profilePictureRepository.findById(id);
  }

  public Optional<ProfilePicture> get(@NonNull long id, @NonNull Profile user) {
    return profilePictureRepository.findFirstByIdAndUser(id, user);
  }

  public void delete(@NonNull ProfilePicture image) throws IOException {
//    storageService.delete(image.getPath());
    profilePictureRepository.delete(image);
  }

  public ProfilePicture save(@NonNull ProfilePicture image) {
    return profilePictureRepository.save(image);
  }

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

  public Resource retrieve(@NonNull ProfilePicture image) throws IOException {
//    return storageService.retrieve(image.getPath());
    return null;
  }

}
