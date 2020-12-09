package edu.cnm.deepdive.albuquirky.service;

import edu.cnm.deepdive.albuquirky.model.dao.OrderRepository;
import edu.cnm.deepdive.albuquirky.model.dao.ProductOnOrderRepository;
import edu.cnm.deepdive.albuquirky.model.dao.ProfileRepository;
import edu.cnm.deepdive.albuquirky.model.entity.Order;
import edu.cnm.deepdive.albuquirky.model.entity.ProductOnOrder;
import edu.cnm.deepdive.albuquirky.model.entity.Profile;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class handles all of the business logic for getting, putting, posting, and deleting items
 * from the {@link Order} on behalf of the {@link OrderService} class, using methods from the
 * {@link OrderRepository} interface.
 */
@Service
public class OrderService {

  private final OrderRepository orderRepository;
  private final ProfileRepository profileRepository;
  private final ProductOnOrderRepository productOnOrderRepository;

  /**
   * The constructor creates instances of {@link OrderRepository} and {@link ProfileRepository}.
   * @param orderRepository The {@link OrderRepository} to be created.
   * @param profileRepository The {@link ProfileRepository} to be created.
   * @param productOnOrderRepository The {@link ProductOnOrderRepository} to be created.
   */
  @Autowired
  public OrderService(OrderRepository orderRepository,
      ProfileRepository profileRepository, ProductOnOrderRepository productOnOrderRepository) {
    this.orderRepository = orderRepository;
    this.profileRepository = profileRepository;
    this.productOnOrderRepository = productOnOrderRepository;
  }

  /**
   * Saves an {@link Order} in the database.
   * @param order The {@link Order} to be saved.
   * @param profile The {@link Profile} of the user that placed the order.
   * @return The {@link Order} that was placed.
   */
  public Order save(Order order, Profile profile) {
    return profileRepository.findById(order.getBuyer().getId())
        .map((buyer) -> {
          order.setBuyer(buyer);
          order.setBuyer(profile);
          return orderRepository.save(order);
        })
        .orElseThrow(NoSuchElementException::new);
  }

  /**
   * Retrieves a specific {@link Order} by ID.
   * @param id The ID of the {@link Order} being requested.
   * @return An {@code Optional} containing the matching {@code Order}.
   */
  public Optional<Order> get(long id) {
    return orderRepository.findById(id);
  }

  /**
   * Retrieves all orders placed by a specific buyer.
   * @param profile The {@link Profile} of the user who placed the orders.
   * @return A {@code List} of {@link Order} objects placed by the specified user.
   */
  public List<Order> getByBuyer(Profile profile) {
    return orderRepository.getAllByBuyerOrderByPlacedDate(profile);
  }

  /**
   * Retrieves all orders sold by a specific seller.
   * @param profile The {@link Profile} of the user who sold the orders.
   * @return A {@code List} of {@link Order} objects sold by the specified user.
   */
  public List<Order> getBySeller(Profile profile) {
//    return orderRepository.getAllBySellerOrderByPlacedDate(profile);
    return null;
  }

  public List<ProductOnOrder> getProductsOnOrder(long id) {
    return productOnOrderRepository.getAllByOrderId(id);
  }

  public ProductOnOrder saveProductOnOrder(ProductOnOrder productOnOrder, Order order) {
    productOnOrder.setOrder(order);
    return productOnOrderRepository.save(productOnOrder);
  }

}
