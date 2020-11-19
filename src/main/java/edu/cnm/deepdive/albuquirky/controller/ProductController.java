package edu.cnm.deepdive.albuquirky.controller;

import edu.cnm.deepdive.albuquirky.model.entity.Product;
import edu.cnm.deepdive.albuquirky.model.entity.Profile;
import edu.cnm.deepdive.albuquirky.service.ProductService;
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

/**
 * The ProductController class is the @RestController that maps the endpoints of "/products" for
 * communication between the server-side and client-side for {@link Product}
 */
@RestController
@RequestMapping("/products")
@ExposesResourceFor(Product.class)
public class ProductController {

  private final ProductService productService;

  /**
   * Constructs the instance of ProductService object
   * @param productService
   */
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  /**
   * The Get method which returns products matching a keyword
   * @param keyword
   * @return
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Product> getProductsMatchingKeyword(@PathVariable String keyword) {
    return productService.getByName(keyword);
  }

  /**
   * The Get method which returns products from a seller
   * @param auth
   * @return
   */
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Product> getProductsWhereSelling(Authentication auth) {
    return productService.getByProfile(getAuthProfile(auth));
  }

  // TODO: POST

  /**
   * The Get method which returns a product from a product id
   * @param productId
   * @return
   */
  @GetMapping(value = "/{productId:\\d+}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Product getProduct(@PathVariable long productId) {
    return productService.get(productId).orElseThrow(NoSuchElementException::new);
  }

  /**
   * The Get method which returns a product from the product name
   * @param productId
   * @return
   */
  @GetMapping(value = "/{productId:\\d+}/name",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public String getName(@PathVariable long productId) {
    Product product = getProduct(productId);
    return product.getName();
  }

  /**
   * The Put method which updates the name of a product
   * @param name
   * @param productId
   * @return
   */
  @PutMapping(value = "/{productId:\\d+}/name",
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public String updateName(
      @RequestParam String name, @PathVariable long productId) {
    Product product = getProduct(productId);
    product.setName(name);
    return productService.save(product).getName();
  }

  /**
   * The Get method which returns the description of a product
   * @param productId
   * @return
   */
  @GetMapping(value = "/{productId:\\d+}/description",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public String getDescription(@PathVariable long productId) {
    Product product = getProduct(productId);
    return product.getDescription();
  }

  /**
   * The Put method which updates the description of a product
   * @param description
   * @param productId
   * @return
   */
  @PutMapping(value = "/{productId:\\d+}/description",
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public String updateDescription(@RequestParam String description, @PathVariable long productId) {
    Product product = getProduct(productId);
    product.setDescription(description);
    return productService.save(product).getDescription();
  }

  /**
   * The Get method which returns the price of a product
   * @param productId
   * @return
   */
  @GetMapping(value = "/{productId:\\d+}/price",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public int getPrice(@PathVariable long productId) {
    Product product = getProduct(productId);
    return product.getPrice();
  }

  /**
   * The Put method which updates the price of a product
   * @param price
   * @param productId
   * @return
   */
  @PutMapping(value = "/{productId:\\d+}/price",
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public int updatePrice(
      @RequestParam int price, @PathVariable long productId) {
    Product product = getProduct(productId);
    product.setPrice(price);
    return productService.save(product).getPrice();
  }

  /**
   * The Get method which returns the stock of a product
   * @param productId
   * @return
   */
  @GetMapping(value = "/{productId:\\d+}/stock",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public int getStock(@PathVariable long productId) {
    Product product = getProduct(productId);
    return product.getPrice();
  }

  /**
   * The Put method which updates the stock of a product
   * @param stock
   * @param productId
   * @return
   */
  @PutMapping(value = "/{productId:\\d+}/stock",
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public int updateStock(
      @RequestParam int stock, @PathVariable long productId) {
    Product product = getProduct(productId);
    product.setStock(stock);
    return productService.save(product).getStock();
  }

  private static Profile getAuthProfile(Authentication auth) {
    return (Profile) auth.getPrincipal();
  }

}