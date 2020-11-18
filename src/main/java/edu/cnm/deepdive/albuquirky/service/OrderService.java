package edu.cnm.deepdive.albuquirky.service;

import edu.cnm.deepdive.albuquirky.model.dao.OrderRepository;
import edu.cnm.deepdive.albuquirky.model.dao.ProfileRepository;
import edu.cnm.deepdive.albuquirky.model.entity.Order;
import edu.cnm.deepdive.albuquirky.model.entity.Profile;
import java.util.NoSuchElementException;

public class OrderService {

  private final OrderRepository orderRepository;
  private final ProfileRepository profileRepository;

  public OrderService(OrderRepository orderRepository,
      ProfileRepository profileRepository) {
    this.orderRepository = orderRepository;
    this.profileRepository = profileRepository;
  }

  public Order save(Order order, Profile profile) {
    return profileRepository.findById(order.getBuyer().getId())
        .map((buyer) -> {
          order.setBuyer(buyer);
          order.setBuyer(profile);
          return orderRepository.save(order);
        })
        .orElseThrow(NoSuchElementException::new);
  }

  public<Order> OrderService(Profile profile) {
    return orderRepository.getAllByBuyer(profile);
  }
}
