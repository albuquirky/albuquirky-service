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
 * This is the {@code ProductOnOrder} entity class, which declares the attributes needed for products on order
 * which include, {@link ProductOnOrder#id}, {@link ProductOnOrder#itemQuantity} and the unit price.
 * Two attributes that are annotated @ManyToOne are the {@link Product} id and the {@link Order} id.
 */
@Entity
public class ProductOnOrder {

  /**
   * The Primary Key for the class, the product on order id
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "product_on_order_id", nullable = false, updatable = false)
  private Long id;

  /**
   * This is the ManyToOne side of the of the relationship between {@link Product} and
   * {@link ProductOnOrder}.
   */
  @NonNull
  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false, updatable = false)
  private Product product;

  /**
   * This is the ManyToOne side of the of the relationship between {@link Order} and
   * {@link ProductOnOrder}.
   */
  @NonNull
  @ManyToOne
  @JoinColumn(name = "order_id", nullable = false, updatable = false)
  private Order order;

  /**
   * The item quantity for product on order
   */
  @NonNull
  @Column(updatable = false, nullable = false)
  private int itemQuantity;

  /**
   * Unit price for a product on order
   */
  @NonNull
  @Column(updatable = false, nullable = false)
  private int unitPrice;

  /**
   * Returns the {@link ProductOnOrder#id}
   */
  public Long getId() {
    return id;
  }

  /**
   * Returns the {@link Product} id
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
