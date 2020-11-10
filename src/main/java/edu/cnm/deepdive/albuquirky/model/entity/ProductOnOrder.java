package edu.cnm.deepdive.albuquirky.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.lang.NonNull;

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

  public Long getId() {
    return id;
  }

  @NonNull
  public Product getProduct() {
    return product;
  }

  public void setProduct(@NonNull Product product) {
    this.product = product;
  }

  @NonNull
  public Order getOrder() {
    return order;
  }

  public void setOrder(@NonNull Order order) {
    this.order = order;
  }

  public int getItemQuantity() {
    return itemQuantity;
  }

  public void setItemQuantity(int itemQuantity) {
    this.itemQuantity = itemQuantity;
  }

  public int getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(int unitPrice) {
    this.unitPrice = unitPrice;
  }

}
