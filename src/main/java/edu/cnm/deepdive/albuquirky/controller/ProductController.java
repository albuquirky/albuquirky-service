package edu.cnm.deepdive.albuquirky.controller;

import edu.cnm.deepdive.albuquirky.model.dao.ProductRepository;
import edu.cnm.deepdive.albuquirky.service.ProductService;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 */
@RestController
public class ProductController {

  private final ProductService productService;

  /**
   * TODO
   * @param productService
   */
  public ProductController(ProductService productService) {
    this.productService = productService;

  }

}