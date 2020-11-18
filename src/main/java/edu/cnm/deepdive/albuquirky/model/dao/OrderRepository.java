package edu.cnm.deepdive.albuquirky.model.dao;

import edu.cnm.deepdive.albuquirky.model.entity.Commission;
import edu.cnm.deepdive.albuquirky.model.entity.Order;
import edu.cnm.deepdive.albuquirky.model.entity.Profile;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

  List<Order> getAllByBuyer(Profile buyer);


}
