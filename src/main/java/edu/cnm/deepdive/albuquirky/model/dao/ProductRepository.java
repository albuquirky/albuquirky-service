package edu.cnm.deepdive.albuquirky.model.dao;

import edu.cnm.deepdive.albuquirky.model.entity.Product;
import edu.cnm.deepdive.albuquirky.model.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The ProductRepository contains methods used to query the database for items in the
 * {@link Product} table.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

  /**
   * The Get method to return all {@link Product} objects sold by a particular {@link Profile}.
   * @param profile The {@link Profile} with which the desired {@link Product} objects are
   *                associated.
   * @return An {@code Iterable} containing all {@link Product} objects associated with the given
   * {@link Profile}.
   */
  Iterable<Product> getAllByProfileOrderByName(Profile profile);

  /**
   * The Get method to return all {@link Product} objects with a partial match to the keyword by
   * name.
   * @param nameFragment A String matched to a {@link Product} name.
   * @return An {@code Iterable} containing all {@link Product} objects that match the keyword.
   */
  Iterable<Product> getAllByNameContainsOrderByName(String nameFragment);

  /**
   * The Get method to return all {@link Product} objects with a partial match to the keyword by
   * name, sold by a particular {@link Profile}.
   * @param profile The {@link Profile} that sells the requested {@link Product} objects.
   * @param nameFragment A String matched to a {@link Product} name.
   * @return An {@code Iterable} containing all {@link Product} objects that match the keyword and
   * are sold by the requested {@link Profile}.
   */
  Iterable<Product> getAllByProfileAndNameContainsOrderByName(Profile profile, String nameFragment);

}
