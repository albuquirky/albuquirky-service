package edu.cnm.deepdive.albuquirky.controller;

import edu.cnm.deepdive.albuquirky.model.entity.Image;
import edu.cnm.deepdive.albuquirky.model.entity.Product;
import edu.cnm.deepdive.albuquirky.model.entity.Profile;
import edu.cnm.deepdive.albuquirky.service.ImageService;
import edu.cnm.deepdive.albuquirky.service.ProductService;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The ImageController class is the @RestController that maps the endpoints of "/images" for the
 * communication between the server-side and client-side for {@link Image}
 */
@RestController
@RequestMapping("/images")
@ExposesResourceFor(Image.class)
public class ImageController {

  private final ImageService imageService;
  private final ProductService productService;

  /**
   * Constructs the instances of {@link ImageService} object and {@link ProductService} object.
   * @param imageService The instance of {@link ImageService} to initialize.
   * @param productService The instance of {@link ProductService} to initialize.
   */
  public ImageController(ImageService imageService, ProductService productService) {
    this.imageService = imageService;
    this.productService = productService;
  }

  /**
   * The Get method which returns a list of {@link Image} objects for a {@link Product}.
   * @param productId The ID of the {@link Product} the user wants images for.
   * @return A {@code List} of {@link Image} objects for the specified {@link Product}.
   */
  @GetMapping(value = "/{productId:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Image> getProductImages(@PathVariable long productId) {
    Product product = productService.get(productId).orElseThrow(NoSuchElementException::new);
    return imageService.getAllByProduct(product);
  }

  /**
   * The Post method for creating a new image.
   * @param image The {@link Image} to be created.
   * @return The {@link Image} object that was created.
   */
  @PostMapping(
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
  public Image post(@RequestBody Image image) {
    return imageService.save(image);
  }


  /**
   * The Get method for retrieving an {@link Image} object by its ID.
   * @param imageId The ID of the {@link Image} object to be retrieved.
   * @return The matching {@link Image} object.
   */
  @GetMapping(value = "/{imageId:\\d+}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Image getImage(@PathVariable long imageId) {
    return imageService.get(imageId).orElseThrow(NoSuchElementException::new);
  }

  /**
   * The Delete method that returns a response entity indicating success or failure.
   * @param imageId The ID of the {@link Image} to be deleted.
   * @param image The {@link Image} object.
   * @param profile The {@link Profile} of the user requesting the delete.
   * @return A {@code ResponseEntity} indicating the status of the delete attempt.
   */
  @DeleteMapping(value = "/{imageId:\\d+}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Long> deleteImage(
      @PathVariable long imageId, Image image, Profile profile) {
    imageService.remove(image, profile);
    return imageService.get(imageId).isEmpty()
        ? new ResponseEntity<>(imageId, HttpStatus.OK)
        : new ResponseEntity<>(imageId, HttpStatus.NOT_FOUND);
  }

  /**
   * The Get method which returns the description of an image.
   * @param imageId The ID of the {@link Image} object for which the description is required.
   * @return The String description of the specified {@link Image}.
   */
  @GetMapping(value = "/{imageId:\\d+}/description",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public String getDescription(@PathVariable long imageId) {
    Image image = getImage(imageId);
    return image.getImageDescription();
  }

  /**
   * The Put method which allows updating the description of an image.
   * @param description The new description contents.
   * @param imageId The {@link Image} object to be updated.
   * @return The description contents of the {@link Image} after being updated.
   */
  @PutMapping(value = "/{imageId:\\d+}/description",
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
  public String updateDescription(@RequestBody String description, @PathVariable long imageId) {
    Image image = getImage(imageId);
    image.setImageDescription(description);
    return imageService.save(image).getImageDescription();
  }

}
