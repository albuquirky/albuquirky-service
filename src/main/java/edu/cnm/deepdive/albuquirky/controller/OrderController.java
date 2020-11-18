package edu.cnm.deepdive.albuquirky.controller;

import edu.cnm.deepdive.albuquirky.service.OrderService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }
}
