package edu.cnm.deepdive.albuquirky.model.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

/**
 * This is the Order Entity class, which declares the attributes needed for orders. Those include
 * the order ID, a timestamp when the order was placed and buyer ID which is annotated by a ManyToOne
 * from {@link Profile}. A OneToMany annotation for items on order which returns a list of items on
 * order coming from {@link ProductOnOrder}.
 */
@Entity
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "order_id", nullable = false, updatable = false)
  private Long id;

  @NonNull
  @ManyToOne
  @JoinColumn(name = "profile_id", nullable = false, updatable = false)
  private Profile profile;

  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date placed_date;

  public Long getId() {
    return id;
  }

  @NonNull
  public Profile getProfile() {
    return profile;
  }

  @NonNull
  public Date getPlaced_date() {
    return placed_date;
  }

  public void setPlaced_date(@NonNull Date placed_date) {
    this.placed_date = placed_date;
  }

}
