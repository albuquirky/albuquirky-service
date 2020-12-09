package edu.cnm.deepdive.albuquirky.model.dao;

import edu.cnm.deepdive.albuquirky.model.entity.Order;
import edu.cnm.deepdive.albuquirky.model.entity.ProductOnOrder;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The ProductOnOrderRepository contains methods used to query the database for items in the
 * {@link ProductOnOrder} table.
 */
public interface ProductOnOrderRepository extends JpaRepository<ProductOnOrder, Long> {

  /**
   * Returns all products on a specific order.
   * @param id The ID of the {@link Order} placed.
   * @return A {@code List} of {@link ProductOnOrder} matching the provided {@link Order}.
   */
  List<ProductOnOrder> getAllByOrderId(long id);

}
