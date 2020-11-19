package edu.cnm.deepdive.albuquirky.service;

import edu.cnm.deepdive.albuquirky.model.dao.ProductRepository;
import edu.cnm.deepdive.albuquirky.model.entity.Product;
import edu.cnm.deepdive.albuquirky.model.entity.Profile;
import java.security.InvalidKeyException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This @Service class holds the logic for queries involing a product in the AlbuQuirky database
 */
@Service
public class ProductService {

  /**
   * Field is a reference to {@link ProductRepository}
   */
  private final ProductRepository productRepository;

  /**
   * Constructs the instance of ProductRepository
   * @param productRepository- instance of product
   */
  @Autowired
  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  /**
   * Saves the product
   * @param product
   * @return
   */
  public Product save(Product product) {
    return productRepository.save(product);
  }

  /**
   * Saves the product from a profile
   * @param product- a product
   * @param profile- Profile account
   * @return a product saved to a profile
   */
  public Product save(Product product, Profile profile) {
    product.setProfile(profile);
    // TODO validate product info.
    return productRepository.save(product);
  }

  /**
   * Deletes a product
   * @param product- a
   * @param profile- profile
   */
  public Product remove(Product product, Profile profile) {
    if (!product.getProfile().getId().equals(profile.getId())) {
      // TODO throw an exception indicating access is forbidden
    }
    // TODO explore business logic for product disabling.  Consider not deleting."Currently unavailable"
    productRepository.delete(product);
    return product;
  }

  /**
   * Returns a product by id
   * @param id- product id
   */
  public Optional<Product> get(long id) {
    return productRepository.findById(id);
  }

  /**
   * Returns a profile by a product
   * @param profile- profile
   */
  public Iterable<Product> getByProfile(Profile profile) {
    return productRepository.getAllByProfileOrderByName(profile);
  }

  /**
   * Returns a product by a name
   * @param nameFragment
   */
  public Iterable<Product> getByName(String nameFragment) {
    return productRepository.getAllByNameContainsOrderByName(nameFragment);
  }

  /**
   * Returns a product by profile and name
   * @param profile
   * @param nameFragment
   */
  public Iterable<Product> getByProfileAndName(Profile profile, String nameFragment) {
    return productRepository.getAllByProfileAndNameContainsOrderByName(profile, nameFragment);
  }

  /**
   * Returns all products
   */
  public Iterable<Product> getAll() {
    return productRepository.findAll();
  }

}
