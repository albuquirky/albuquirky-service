package edu.cnm.deepdive.albuquirky.controller;

import edu.cnm.deepdive.albuquirky.model.entity.Image;
import edu.cnm.deepdive.albuquirky.model.entity.Product;
import edu.cnm.deepdive.albuquirky.service.ImageService;
import edu.cnm.deepdive.albuquirky.service.ProductService;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
   * Constructs the instances of ImageService object and ProductService object
   * @param imageService
   * @param productService
   */
  public ImageController(ImageService imageService, ProductService productService) {
    this.imageService = imageService;
    this.productService = productService;
  }

  /**
   * The Get method which returns a list of product images
   * @param productId
   * @return
   */
  @GetMapping(value = "/{productId:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Image> getProductImages(@PathVariable long productId) {
    Product product = productService.get(productId).orElseThrow(NoSuchElementException::new);
    return imageService.getAllByProduct(product);
  }

  // TODO: POST

  /**
   * The Get method which returns an image id
   * @param imageId
   * @return
   */
  @GetMapping(value = "/{imageId:\\d+}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Image getImage(@PathVariable long imageId) {
    return imageService.get(imageId).orElseThrow(NoSuchElementException::new);
  }

  // TODO: DELETE

  /**
   * The Get method which returns the description of an image
   * @param imageId
   * @return
   */
  @GetMapping(value = "/{imageId:\\d+}/description",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public String getDescription(@PathVariable long imageId) {
    Image image = getImage(imageId);
    return image.getImageDescription();
  }

  /**
   * The Put method which allows updating the description of an image
   * @param description
   * @param imageId
   * @return
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
