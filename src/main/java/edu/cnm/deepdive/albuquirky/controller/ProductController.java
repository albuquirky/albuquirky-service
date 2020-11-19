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

@RestController
@RequestMapping("/products")
@ExposesResourceFor(Product.class)
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping(value = "/{keyword}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Product> getProductsMatchingKeyword(@PathVariable String keyword) {
    return productService.getByName(keyword);
  }

  @GetMapping(value = "/seller", produces = MediaType.APPLICATION_JSON_VALUE)
  public Iterable<Product> getProductsWhereSelling(Authentication auth) {
    return productService.getByProfile(getAuthProfile(auth));
  }

  // TODO: POST

  @GetMapping(value = "/{productId:[0-9a-fA-F\\-]{32,}}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Product getProduct(@PathVariable long productId) {
    return productService.get(productId).orElseThrow(NoSuchElementException::new);
  }

  @GetMapping(value = "/{productId:[0-9a-fA-F\\-]{32,}}/name",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public String getName(@PathVariable long productId) {
    Product product = getProduct(productId);
    return product.getName();
  }

  @PutMapping(value = "/{productId:[0-9a-fA-F\\-]{32,}}/name",
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public String updateName(
      @RequestParam String name, @PathVariable long productId) {
    Product product = getProduct(productId);
    product.setName(name);
    return productService.save(product).getName();
  }

  @GetMapping(value = "/{productId:[0-9a-fA-F\\-]{32,}}/description",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public String getDescription(@PathVariable long productId) {
    Product product = getProduct(productId);
    return product.getDescription();
  }

  @PutMapping(value = "/{productId:[0-9a-fA-F\\-]{32,}}/description",
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public String updateDescription(@RequestParam String description, @PathVariable long productId) {
    Product product = getProduct(productId);
    product.setDescription(description);
    return productService.save(product).getDescription();
  }

  @GetMapping(value = "/{productId:[0-9a-fA-F\\-]{32,}}/price",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public int getPrice(@PathVariable long productId) {
    Product product = getProduct(productId);
    return product.getPrice();
  }

  @PutMapping(value = "/{productId:[0-9a-fA-F\\-]{32,}}/price",
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public int updatePrice(
      @RequestParam int price, @PathVariable long productId) {
    Product product = getProduct(productId);
    product.setPrice(price);
    return productService.save(product).getPrice();
  }

  @GetMapping(value = "/{productId:[0-9a-fA-F\\-]{32,}}/stock",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public int getStock(@PathVariable long productId) {
    Product product = getProduct(productId);
    return product.getPrice();
  }

  @PutMapping(value = "/{productId:[0-9a-fA-F\\-]{32,}}/stock",
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