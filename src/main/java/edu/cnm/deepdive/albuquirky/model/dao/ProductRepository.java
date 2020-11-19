package edu.cnm.deepdive.albuquirky.model.dao;

import edu.cnm.deepdive.albuquirky.model.entity.Product;
import edu.cnm.deepdive.albuquirky.model.entity.Profile;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

  Optional<Product> getById(Long id);

  Iterable<Product> getAllByProfileOrderByName(Profile profile);

  Iterable<Product> getAllByNameContainsOrderByName(String nameFragment);

  Iterable<Product> getAllByProfileAndNameContainsOrderByName(Profile profile, String nameFragment);

}
