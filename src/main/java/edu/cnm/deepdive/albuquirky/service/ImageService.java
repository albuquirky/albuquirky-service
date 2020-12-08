package edu.cnm.deepdive.albuquirky.service;

import edu.cnm.deepdive.albuquirky.model.dao.ImageRepository;
import edu.cnm.deepdive.albuquirky.model.entity.Image;
import edu.cnm.deepdive.albuquirky.model.entity.Product;
import edu.cnm.deepdive.albuquirky.model.entity.Profile;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class handles all of the business logic for getting, putting, posting, and deleting items
 * from the {@link Image} on behalf of the {@link ImageService} class, using methods from the
 * {@link ImageRepository} interface.
 */
@Service
public class ImageService {

  private final ImageRepository imageRepository;

  /**
   * Creates instances of the ImageRepository.
   * @param imageRepository The {@link ImageRepository} to be created.
   */
  @Autowired
  public ImageService(ImageRepository imageRepository) {
    this.imageRepository = imageRepository;
  }

  /**
   * Method to save images pertaining to the user profile or product listing.
   * @param image The {@link Image} to be saved.
   * @return The {@link Image} that was saved.
   */
  public Image save(Image image) {
    return imageRepository.save(image);
  }

  /**
   * This will remove an image if needed by the user it pertains to. It will also throw an
   * exception if someone without access attempts to remove the photo.
   * @param image The {@link Image} object to be deleted.
   * @param profile The {@link Profile} connected to the {@link Image}.
   * @return The {@link Image} object that was deleted.
   */
  public Image remove(Image image, Profile profile) {
    if (!image.getProduct().getProfile().getId().equals(profile.getId())) {
      // TODO throw an exception indicating access is forbidden
    } else {
      imageRepository.delete(image);
    }
    return image;
  }

  /**
   * Method to get a specific image.
   * @param id The ID of the {@link Image} being requested.
   * @return The matching {@link Image}.
   */
  public Optional<Image> get(long id) {
    return imageRepository.findById(id);
  }

  /**
   * Method to get images tied to a specific product.
   * @param product The {@link Product} linked to the images requested.
   * @return A {@code List} of {@link Image} objects associated with the {@link Product}.
   */
  public List<Image> getAllByProduct(Product product) {
    return imageRepository.getAllByProductOrderByCreatedAsc(product);
  }

}
