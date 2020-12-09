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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

/**
 *  This is the {@code Commission} entity class, which declares the attributes needed for each
 *  commission which includes, the commission ID, the commission request, waitlist position, the
 *  seller ID, a timestamp, the {@link Commission} ID and the {@link Product} ID. Both seller ID and
 *   commissioner ID are annotated by @ManyToOne coming from {@link Profile}. Commissions accepted
 *   by the commissioner become {@link Product}.
 */
@Entity
@Table(indexes = {@Index(columnList = "waitlistPosition")})
public class Commission {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "commission_id", nullable = false, updatable = false)
  private Long id;

  @NonNull
  @Column(length = 200, nullable = false)
  private String commissionRequest;

  @NonNull
  private int waitlistPosition;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "seller_id")
  private Profile seller;

  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false)
  private Date timestamp;

  @NonNull
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "commissioner_id", nullable = false)
  private Profile commissioner;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "product_id")
  private Product product;


  /**
   * Gets the ID of the {@link Commission}.
   * @return The {@link Commission}.
   */
  public Long getId() {
    return id;
  }

  /**
   * Returns the commission request for the {@link Commission}.
   * @return The commission request for the {@link Commission}.
   */
  @NonNull
  public String getCommissionRequest() {
    return commissionRequest;
  }

  /**
   * Sets the commission request for the {@link Commission}.
   * @param commissionRequest The commission request content.
   */
  public void setCommissionRequest(@NonNull String commissionRequest) {
    this.commissionRequest = commissionRequest;
  }

  /**
   * Gets the waitlist position for the commission.
   * @return The current waitlist position.
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
   * Gets the commission creation timestamp.
   * @return The timestamp from when the commission was created.
   */
  @NonNull
  public Date getTimestamp() {
    return timestamp;
  }

  /**
   * Gets the commissioning {@link Profile}.
   * @return The {@link Profile} who created the commission.
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
   * Gets the {@link Product} associated with the commission.
   * @return product- Product
   */
  public Product getProduct() {
    return product;
  }

  /**
   * Sets the {@link Product} associated with the commission.
   * @param product- Product
   */
  public void setProduct(Product product) {
    this.product = product;
  }

}
