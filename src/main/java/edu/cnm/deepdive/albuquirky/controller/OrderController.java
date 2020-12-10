package edu.cnm.deepdive.albuquirky.controller;

import edu.cnm.deepdive.albuquirky.model.entity.Order;
import edu.cnm.deepdive.albuquirky.model.entity.Product;
import edu.cnm.deepdive.albuquirky.model.entity.Profile;
import edu.cnm.deepdive.albuquirky.service.OrderService;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The OrderController class is the @RestController that maps the endpoints of "/orders" for
 * communication between the server-side and client-side for {@link Order}.
 */
@RestController
@RequestMapping("/orders")
@ExposesResourceFor(Order.class)
public class OrderController {

  private final OrderService orderService;

  /**
   * Constructs the instance of OrderService object.
   * @param orderService The instance of {@link OrderService} to initialize.
   */
  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  /**
   * The Get method which returns a list of the user's placed orders.
   * @param auth The user authentication.
   * @return A {@code List} of {@link Order} objects representing the user's placed orders.
   */
  @GetMapping(value = "/user-orders", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Order> getUserOrders(Authentication auth) {
    return orderService.getByBuyer(getAuthProfile(auth));
  }

  /**
   * The Get method which returns a list of the orders the user is responsible for fulfilling.
   * @param auth The user authentication.
   * @return A {@code List} of {@link Order} objects representing the orders the user is responsible
   *  for fulfilling.
   */
  @GetMapping(value = "/user-sold-orders", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Order> getUserSoldOrders(Authentication auth) {
    return orderService.getBySeller(getAuthProfile(auth));
  }

  /**
   * The Get method which returns an order by the order ID.
   * @param orderId The ID of the {@link Order} object requested.
   * @return The {@link Order} object with the ID requested.
   */
  @GetMapping(value = "/{orderId:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Order getOrder(@PathVariable long orderId) {
    return orderService.get(orderId).orElseThrow(NoSuchElementException::new);
  }

  /**
   * The Post method for creating an order.
   * @param order The {@link Order} to be created.
   * @param product The {@link Product} on the order.
   * @param auth The authorization for the user.
   * @return The {@link Order} object that was created.
   */
  @PostMapping(
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
  public Order post(@RequestBody Order order, Product product, Authentication auth) {
    return orderService.save(order, getAuthProfile(auth));
  }

  private static Profile getAuthProfile(Authentication auth) {
    return (Profile) auth.getPrincipal();
  }

}
