package edu.cnm.deepdive.albuquirky.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.lang.NonNull;

/**
 * This is the {@code ProductOnOrder} entity class, which declares the attributes needed for
 * products on order which include, {@link ProductOnOrder#id}, {@link ProductOnOrder#itemQuantity}
 * and the unit price. Two attributes that are annotated @ManyToOne are the {@link Product} ID and
 * the {@link Order} ID.
 */
@Entity
public class ProductOnOrder {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "product_on_order_id", nullable = false, updatable = false)
  private Long id;

  @NonNull
  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false, updatable = false)
  private Product product;

  @NonNull
  @ManyToOne
  @JoinColumn(name = "order_id", nullable = false, updatable = false)
  private Order order;

  @NonNull
  @Column(updatable = false, nullable = false)
  private int itemQuantity;

  @NonNull
  @Column(updatable = false, nullable = false)
  private int unitPrice;

  /**
   * Returns the {@link ProductOnOrder#id}
   * @return The ID for the ProductOnOrder.
   */
  public Long getId() {
    return id;
  }

  /**
   * Returns the {@link Product} id
   * @return The ID of the {@link Product}.
   */
  @NonNull
  public Product getProduct() {
    return product;
  }

  /**
   * Sets {@link Product}
   * @param product- Product
   */
  public void setProduct(@NonNull Product product) {
    this.product = product;
  }

  /**
   * Returns the {@link Order} id
   * @return The ID of the {@link Order}.
   */
  @NonNull
  public Order getOrder() {
    return order;
  }

  /**
   * Sets {@link Order}
   * @param order- Order
   */
  public void setOrder(@NonNull Order order) {
    this.order = order;
  }

  /**
   * Returns the {@link ProductOnOrder#itemQuantity}
   * @return The quantity of the {@link Product} on the order.
   */
  public int getItemQuantity() {
    return itemQuantity;
  }

  /**
   * Sets the {@link ProductOnOrder#itemQuantity}
   * @param itemQuantity- int
   */
  public void setItemQuantity(int itemQuantity) {
    this.itemQuantity = itemQuantity;
  }

  /**
   * Returns the {@link ProductOnOrder#unitPrice}
   * @return The price for the {@link Product}.
   */
  public int getUnitPrice() {
    return unitPrice;
  }

  /**
   * Sets the {@link ProductOnOrder#unitPrice}
   * @param unitPrice- int
   */
  public void setUnitPrice(int unitPrice) {
    this.unitPrice = unitPrice;
  }

}
