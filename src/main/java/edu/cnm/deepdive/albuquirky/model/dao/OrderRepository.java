package edu.cnm.deepdive.albuquirky.model.dao;

import edu.cnm.deepdive.albuquirky.model.entity.Order;
import edu.cnm.deepdive.albuquirky.model.entity.Profile;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The OrderRepository interface gives an Optional {@link Order} by when the order was placed.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

  /**
   * The getAllByBuyerOrderByPlaceDate query list orders placed by the buyer ordered by date.
   * @param buyer The current {@link Profile} logged in.
   * @return The {@code List} of {@link Order} objects the user placed.
   */
  List<Order> getAllByBuyerOrderByPlacedDate(Profile buyer);

  /**
   * The getAllBySellerOrderByPlacedDate query lists orders sold by the user, ordered by the date
   * placed.
   * @param seller The current {@link Profile} logged in.
   * @return The {@code List} of {@link Order} objects the user sold.
   */
  List<Order> getAllBySellerOrderByPlacedDate(Profile seller);

}
