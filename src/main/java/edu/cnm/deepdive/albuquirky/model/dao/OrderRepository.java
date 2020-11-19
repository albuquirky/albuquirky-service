package edu.cnm.deepdive.albuquirky.model.dao;

import edu.cnm.deepdive.albuquirky.model.entity.Order;
import edu.cnm.deepdive.albuquirky.model.entity.Profile;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The OrderRepository interface gives an Optional {@link Order} by when the order was placed.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

  /**
   * The getAllByBuyerOrderByPlaceDate query list orders placed by the buyer ordered by date.
   * @param buyer current profile logged in
   * @return list of orders for the buyer
   */
  List<Order> getAllByBuyerOrderByPlacedDate(Profile buyer);

}
