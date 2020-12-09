package edu.cnm.deepdive.albuquirky.model.entity;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

/**
 * This is the {@code Order} entity class, which declares the attributes needed for orders. Those
 * include the {@link Order#id}, a {@link Order#placedDate} and buyer id which is annotated by a
 * @ManyToOne from {@link Profile}. A @OneToMany annotation for items on order which returns a list
 * of items on order coming from {@link ProductOnOrder}.
 */
@Entity
@Table(name = "placed_order")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "order_id", nullable = false, updatable = false)
  private Long id;

  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false)
  private Date placedDate;

  @NonNull
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "buyer_id", nullable = false, updatable = false)
  private Profile buyer;

  @NonNull
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "seller_id", nullable = false, updatable = false)
  private Profile seller;

  @NonNull
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "order",
      cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
  @OrderBy("itemQuantity DESC")
  private final List<ProductOnOrder> itemsOnOrder = new LinkedList<>();

  /**
   * Gets the ID for the order.
   * @return The order's ID.
   */
  public Long getId() {
    return id;
  }

  /**
   * Gets the creation timestamp for when the order was placed.
   * @return The creation timestamp.
   */
  @NonNull
  public Date getPlacedDate() {
    return placedDate;
  }

  /**
   * Gets the buying {@link Profile}.
   * @return The {@link Profile} that placed the order.
   */
  @NonNull
  public Profile getBuyer() {
    return buyer;
  }

  /**
   * Sets {@link Order#buyer}
   * @param buyer- Profile
   */
  public void setBuyer(@NonNull Profile buyer) {
    this.buyer = buyer;
  }

  /**
   * Gets the items on the order.
   * @return List of itemsOnOrder
   */
  @NonNull
  public List<ProductOnOrder> getItemsOnOrder() {
    return itemsOnOrder;
  }

}
