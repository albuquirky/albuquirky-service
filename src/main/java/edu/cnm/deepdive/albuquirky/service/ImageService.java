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
 * The ImageService is a class the @Service for the ImageController. This class gives the
 * ImageController the logic necessary to run and perform tasks. {@link Image}
 */
@Service
public class ImageService {

  private final ImageRepository imageRepository;

  /**
   * Creates instances of the ImageRepository.
   * @param imageRepository
   */
  @Autowired
  public ImageService(ImageRepository imageRepository) {
    this.imageRepository = imageRepository;
  }

  /**
   * This will be used to save images pertaining to the user profile or product listing.
   * @param image
   * @return
   */
  public Image save(Image image) {
    return imageRepository.save(image);
  }

  /**
   * This will remove an image if needed by the user it pertains to. It will also throw an
   * exception if someone without access attempts to remove the photo.
   * @param image
   * @param profile
   * @return
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
   * @param id
   * @return
   */
  public Optional<Image> get(long id) {
    return imageRepository.findById(id);
  }

  /**
   * This will be used to get images tied to a specific product.
   * @param product
   * @return
   */
  public List<Image> getAllByProduct(Product product) {
    return imageRepository.getAllByProductOrderByCreatedAsc(product);
  }

}
