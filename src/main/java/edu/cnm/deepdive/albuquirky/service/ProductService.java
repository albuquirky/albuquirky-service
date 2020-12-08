package edu.cnm.deepdive.albuquirky.service;

import edu.cnm.deepdive.albuquirky.model.dao.ProductRepository;
import edu.cnm.deepdive.albuquirky.model.entity.Product;
import edu.cnm.deepdive.albuquirky.model.entity.Profile;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class handles all of the business logic for getting, putting, posting, and deleting items
 * from the {@link Product} on behalf of the {@link ProductService} class, using methods from the
 * {@link ProductRepository} interface.
 */
@Service
public class ProductService {

  /**
   * Field is a reference to {@link ProductRepository}.
   */
  private final ProductRepository productRepository;

  /**
   * Constructs the instance of {@link ProductRepository}.
   * @param productRepository The instance of {@link ProductRepository} to be created.
   */
  @Autowired
  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  /**
   * Saves the product into the database.
   * @param product The {@link Product} to be saved.
   * @return The {@link Product} that was saved.
   */
  public Product save(Product product) {
    return productRepository.save(product);
  }

  /**
   * Saves the {@link Profile} reference for a {@link Product}.
   * @param product The {@link Product} to be updated.
   * @param profile The {@link Profile} to be referenced.
   * @return The updated {@link Product}.
   */
  public Product save(Product product, Profile profile) {
    product.setProfile(profile);
    // TODO validate product info.
    return productRepository.save(product);
  }

  /**
   * Returns a product by ID.
   * @param id The {@link Product} ID.
   * @return An {@code Optional} containing the matching {@link Product}.
   */
  public Optional<Product> get(long id) {
    return productRepository.findById(id);
  }

  /**
   * Returns all {@link Product} objects associated with a seller {@link Profile}.
   * @param profile The seller {@link Profile}.
   * @return An {@code Iterable} containing all requested {@link Product} objects sold by the
   * requested {@link Profile}.
   */
  public Iterable<Product> getByProfile(Profile profile) {
    return productRepository.getAllByProfileOrderByName(profile);
  }

  /**
   * Returns all {@link Product} objects with a name matching a keyword.
   * @param nameFragment The search term keyword.
   * @return An {@code Iterable} containing all {@link Product} objects that match the keyword.
   */
  public Iterable<Product> getByName(String nameFragment) {
    return productRepository.getAllByNameContainsOrderByName(nameFragment);
  }

  /**
   * Returns all {@link Product} objects with a name matching a keyword, sold by a particular
   * seller.
   * @param profile The {@link Profile} of the seller.
   * @param nameFragment The search term keyword.
   * @return An {@code Iterable} containing all {@link Product} objects that match the keyword that
   * are sold by the seller {@link Profile}.
   */
  public Iterable<Product> getByProfileAndName(Profile profile, String nameFragment) {
    return productRepository.getAllByProfileAndNameContainsOrderByName(profile, nameFragment);
  }

  /**
   * Returns every {@link Product} object in the database.
   * @return An {@code Iterable} containing every {@link Product} object in the database.
   */
  public Iterable<Product> getAll() {
    return productRepository.findAll();
  }

}
