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
 * This is the {@code Order} entity class, which declares the attributes needed for orders. Those include
 * the {@link Order#id}, a {@link Order#placedDate} and buyer id which is annotated by a @ManyToOne
 * from {@link Profile}. A @OneToMany annotation for items on order which returns a list of items
 * on order coming from {@link ProductOnOrder}.
 */
@Entity
@Table(name = "placed_order")
public class Order {

  /**
   * The primary key of the class, the order id
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "order_id", nullable = false, updatable = false)
  private Long id;
  /**
   * Date timestamp for the placed date of the order
   */
  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false)
  private Date placedDate;

  // TODO: Change to match column name in ERD.
  /**
   * The ManyToOne side of the relationship between {@link Profile} and {@link Order}, the order's
   * buyer.
   */
  @NonNull
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "buyer_id", nullable = false, updatable = false)
  private Profile buyer;
  /**
   * The OneToMany side of the relationship between {@link ProductOnOrder} and {@link Order} which
   * gives a list of items on order
   */
  @NonNull
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "order",
      cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
  @OrderBy("itemQuantity DESC")
  private final List<ProductOnOrder> itemsOnOrder = new LinkedList<>();

  /**
   * Returns the {@link Order#id}
   */
  public Long getId() {
    return id;
  }

  /**
   * Returns the {@link Order#placedDate}
   */
  @NonNull
  public Date getPlacedDate() {
    return placedDate;
  }

  /**
   * Returns the {@link Order#buyer}
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
   *
   * @return List of itemsOnOrder
   */
  @NonNull
  public List<ProductOnOrder> getItemsOnOrder() {
    return itemsOnOrder;
  }

}
