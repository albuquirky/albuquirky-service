package edu.cnm.deepdive.albuquirky.controller;

import edu.cnm.deepdive.albuquirky.model.entity.Order;
import edu.cnm.deepdive.albuquirky.model.entity.ProductOnOrder;
import edu.cnm.deepdive.albuquirky.service.OrderService;
import java.util.List;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The ProductOnOrderController class is the @RestController that maps the endpoints of
 * "/products-on-order" for communication between the server-side and client-side for
 * {@link ProductOnOrder}.
 */
@RestController
@RequestMapping("/products-on-order")
@ExposesResourceFor(ProductOnOrder.class)
public class ProductOnOrderController {

  private final OrderService orderService;

  /**
   * Constructs the instance of OrderService object.
   * @param orderService The instance of {@link OrderService} to initialize.
   */
  public ProductOnOrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  /**
   * The Get method which returns all {@link ProductOnOrder} entities for a particular
   * {@link edu.cnm.deepdive.albuquirky.model.entity.Order}.
   * @param orderId The ID of the {@link edu.cnm.deepdive.albuquirky.model.entity.Order}.
   * @return A {@code List} of {@link ProductOnOrder} objects on the
   * {@link edu.cnm.deepdive.albuquirky.model.entity.Order}.
   */
  @GetMapping(value = "/{orderId:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<ProductOnOrder> getProductsOnOrder(@PathVariable long orderId) {
    return orderService.getProductsOnOrder(orderId);
  }

  /**
   * The Post method to save a {@link ProductOnOrder} in the database.
   * @param productOnOrder The {@link ProductOnOrder} to save.
   * @param order The {@link Order} to link the {@link ProductOnOrder} to.
   * @return The {@link ProductOnOrder} saved.
   */
  @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
  public ProductOnOrder post(@RequestBody ProductOnOrder productOnOrder, Order order) {
    return orderService.saveProductOnOrder(productOnOrder, order);
  }

}
