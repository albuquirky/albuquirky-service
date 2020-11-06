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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

@Entity
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


  // TODO: Change to match column name in ERD.
  @NonNull
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "profile_id", nullable = false, updatable = false)
  private Profile buyerProfile;

  @NonNull
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "order",
      cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
  @OrderBy("itemQuantity DESC")
  private final List<ProductOnOrder> itemsOnOrder = new LinkedList<>();

  public Long getId() {
    return id;
  }

  @NonNull
  public Date getPlacedDate() {
    return placedDate;
  }

  @NonNull
  public Profile getBuyerProfile() {
    return buyerProfile;
  }

  public void setBuyerProfile(@NonNull Profile buyerProfile) {
    this.buyerProfile = buyerProfile;
  }

  @NonNull
  public List<ProductOnOrder> getItemsOnOrder() {
    return itemsOnOrder;
  }

}
