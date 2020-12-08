package edu.cnm.deepdive.albuquirky.model.dao;

import edu.cnm.deepdive.albuquirky.model.entity.Image;
import edu.cnm.deepdive.albuquirky.model.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The ImageRepository contains methods used to query the database for items in the {@link Image}
 * table.
 */
public interface ImageRepository extends JpaRepository<Image, Long> {

  /**
   * Method to return all images associated with a specific {@link Product}.
   * @param product The {@link Product} with which the desired {@link Image} objects are associated.
   * @return A {@code List} of {@link Image} objects associated with the given {@link Product}.
   */
  List<Image> getAllByProductOrderByCreatedAsc(Product product);

}
