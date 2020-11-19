package edu.cnm.deepdive.albuquirky.model.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.IndexColumn;
import org.springframework.lang.NonNull;

/**
 * This is the {@code Commission} entity class, which declares the attributes needed for each commission
 * which includes, the commission id, the commission request, waitlist position, the seller id,
 * a timestamp, the {@link Commission#} id and the {@link Product} id. Both seller id and commissioner
 * id are annotated by @ManyToOne coming from {@link Profile}. Commissions accepted by the commissioner
 * become {@link Product}.
 */
@Entity
@Table(indexes = {@Index(columnList = "waitlistPosition")})
public class Commission {

  /**
   * The Primary Key for the class, the commission id
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "commission_id", nullable = false, updatable = false)
  private Long id;
  /**
   * A commission request
   */
  @NonNull
  @Column(length = 200, nullable = false)
  private String commissionRequest;
  /**
   * The waitlist postion for a commission
   */
  @NonNull
  private int waitlistPosition;
  /**
   * The ManyToOne side of the relationship between {@link Commission} and {@link Profile}. This is
   * is the seller of the commission.
   */
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "seller_id")
  private Profile seller;
  /**
   * Date timestamp for the commission
   */
  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false)
  private Date timestamp;
  /**
   * The ManyToOne side of the relationship between {@link Commission} and {@link Profile}. This is
   * is the buyer of the commission.
   */
  @NonNull
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "commissioner_id", nullable = false)
  private Profile commissioner;
  /**
   *
   */
  @Column(name = "product_id")
  private Long product;

  /**
   * Returns the {@link Commission#id}
   */
  public Long getId() {
    return id;
  }

  /**
   * Returns the {@link Commission#commissionRequest}
   */
  @NonNull
  public String getCommissionRequest() {
    return commissionRequest;
  }

  /**
   * Sets the {@link Commission#commissionRequest}
   * @param commissionRequest- String
   */
  public void setCommissionRequest(@NonNull String commissionRequest) {
    this.commissionRequest = commissionRequest;
  }

  /**
   * Returns the {@link Commission#waitlistPosition}
   */
  public int getWaitlistPosition() {
    return waitlistPosition;
  }

  /**
   * Sets the {@link Commission#waitlistPosition}
   * @param waitlistPosition- int
   */
  public void setWaitlistPosition(int waitlistPosition) {
    this.waitlistPosition = waitlistPosition;
  }

  /**
   * Returns the {@link Profile} id of the seller.
   */
  public Profile getSeller() {
    return seller;
  }

  /**
   * Sets {@link Profile} seller id
   * @param seller- Profile
   */
  public void setSeller(Profile seller) {
    this.seller = seller;
  }

  /**
   * Returns the Date timestamp
   */
  @NonNull
  public Date getTimestamp() {
    return timestamp;
  }

  /**
   * Returns the {@link Commission#commissioner}
   */
  @NonNull
  public Profile getCommissioner() {
    return commissioner;
  }

  /**
   * Setter of the commissioner
    * @param commissioner {@link Profile}
   */
  public void setCommissioner(@NonNull Profile commissioner) {
    this.commissioner = commissioner;
  }

  /**
   *
   * @return product- Long
   */
  public Long getProduct() {
    return product;
  }

  /**
   * Sets
   * @param product- Long
   */
  public void setProduct(Long product) {
    this.product = product;
  }

}
