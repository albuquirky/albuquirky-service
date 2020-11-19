package edu.cnm.deepdive.albuquirky.service;

import edu.cnm.deepdive.albuquirky.model.dao.ImageRepository;
import edu.cnm.deepdive.albuquirky.model.entity.Image;
import edu.cnm.deepdive.albuquirky.model.entity.Product;
import edu.cnm.deepdive.albuquirky.model.entity.Profile;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

  private final ImageRepository imageRepository;

  @Autowired
  public ImageService(ImageRepository imageRepository) {
    this.imageRepository = imageRepository;
  }

  public Image save(Image image) {
    return imageRepository.save(image);
  }

  public Image remove(Image image, Profile profile) {
    if (!image.getProduct().getProfile().getId().equals(profile.getId())) {
      // TODO throw an exception indicating access is forbidden
    } else {
      imageRepository.delete(image);
    }
    return image;
  }

  public Optional<Image> get(long id) {
    return imageRepository.findById(id);
  }

  public List<Image> getAllByProduct(Product product) {
    return imageRepository.getAllByProductOrderByCreatedAsc(product);
  }

}
