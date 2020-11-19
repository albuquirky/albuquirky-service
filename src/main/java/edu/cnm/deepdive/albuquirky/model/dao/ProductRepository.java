package edu.cnm.deepdive.albuquirky.model.dao;

import edu.cnm.deepdive.albuquirky.model.entity.Product;
import edu.cnm.deepdive.albuquirky.model.entity.Profile;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The ProductRepository interface extends {@link JpaRepository}. The interface handles the queries
 * for a product
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

  Iterable<Product> getAllByProfileOrderByName(Profile profile);

  Iterable<Product> getAllByNameContainsOrderByName(String nameFragment);

  Iterable<Product> getAllByProfileAndNameContainsOrderByName(Profile profile, String nameFragment);

}
