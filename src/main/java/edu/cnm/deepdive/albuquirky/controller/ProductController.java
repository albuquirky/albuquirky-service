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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@ExposesResourceFor(Product.class)
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Product> getAllProducts() {
    return productService.getAll();
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, params = "keyword")
  public Iterable<Product> getProductsMatchingKeyword(@RequestParam String keyword) {
    return productService.getByName(keyword);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, params = {"mine"})
  public Iterable<Product> getProductsWhereSelling(@RequestParam boolean mine, Authentication auth) {
    return productService.getByProfile(getAuthProfile(auth));
  }

  @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
  public Product post(@RequestBody Product product, Authentication auth) {
    product.setProfile(getAuthProfile(auth));
    return productService.save(product);
  }

  @GetMapping(value = "/{productId:\\d+}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Product getProduct(@PathVariable long productId) {
    return productService.get(productId).orElseThrow(NoSuchElementException::new);
  }

  @GetMapping(value = "/{productId:\\d+}/name",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public String getName(@PathVariable long productId) {
    Product product = getProduct(productId);
    return product.getName();
  }

  @PutMapping(value = "/{productId:\\d+}/name",
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
  public String updateName(
      @RequestBody String name, @PathVariable long productId) {
    Product product = getProduct(productId);
    product.setName(name);
    return productService.save(product).getName();
  }

  @GetMapping(value = "/{productId:\\d+}/description",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public String getDescription(@PathVariable long productId) {
    Product product = getProduct(productId);
    return product.getDescription();
  }

  @PutMapping(value = "/{productId:\\d+}/description",
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
  public String updateDescription(@RequestBody String description, @PathVariable long productId) {
    Product product = getProduct(productId);
    product.setDescription(description);
    return productService.save(product).getDescription();
  }

  @GetMapping(value = "/{productId:\\d+}/price",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public int getPrice(@PathVariable long productId) {
    Product product = getProduct(productId);
    return product.getPrice();
  }

  @PutMapping(value = "/{productId:\\d+}/price",
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
  public int updatePrice(
      @RequestBody int price, @PathVariable long productId) {
    Product product = getProduct(productId);
    product.setPrice(price);
    return productService.save(product).getPrice();
  }

  @GetMapping(value = "/{productId:\\d+}/stock",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public int getStock(@PathVariable long productId) {
    Product product = getProduct(productId);
    return product.getPrice();
  }

  @PutMapping(value = "/{productId:\\d+}/stock",
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
  public int updateStock(
      @RequestBody int stock, @PathVariable long productId) {
    Product product = getProduct(productId);
    product.setStock(stock);
    return productService.save(product).getStock();
  }

  private static Profile getAuthProfile(Authentication auth) {
    return (Profile) auth.getPrincipal();
  }

}