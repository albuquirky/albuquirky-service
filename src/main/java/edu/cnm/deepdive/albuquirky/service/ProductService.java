package edu.cnm.deepdive.albuquirky.service;

import edu.cnm.deepdive.albuquirky.model.dao.ProductRepository;
import edu.cnm.deepdive.albuquirky.model.entity.Product;
import edu.cnm.deepdive.albuquirky.model.entity.Profile;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  private final ProductRepository productRepository;

  @Autowired
  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Product save(Product product) {
    return productRepository.save(product);
  }

  public Product save(Product product, Profile profile) {
    product.setProfile(profile);
    // TODO validate product info.
    return productRepository.save(product);
  }

  public Product remove(Product product, Profile profile) {
    if (!product.getProfile().getId().equals(profile.getId())) {
      // TODO throw an exception indicating access is forbidden
    }
    // TODO explore business logic for product disabling.  Consider not deleting
    productRepository.delete(product);
    return product;
  }

  public Optional<Product> get(long id) {
    return productRepository.findById(id);
  }

  public Iterable<Product> getByProfile(Profile profile) {
    return productRepository.getAllByProfileOrderByName(profile);
  }
  public Iterable<Product> getByName(String nameFragment) {
    return productRepository.getAllByNameContainsOrderByName(nameFragment);
  }

  public Iterable<Product> getByProfileAndName(Profile profile, String nameFragment) {
    return productRepository.getAllByProfileAndNameContainsOrderByName(profile, nameFragment);
  }
  
}
