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

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "commission_id", nullable = false, updatable = false)
  private Long id;

  @NonNull
  @Column(length = 200, nullable = false)
  private String commissionRequest;

  @NonNull
  @Column(nullable = false)
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

  @Column(name = "product_id")
  private Long product;

  public Long getId() {
    return id;
  }

  @NonNull
  public String getCommissionRequest() {
    return commissionRequest;
  }

  public void setCommissionRequest(@NonNull String commissionRequest) {
    this.commissionRequest = commissionRequest;
  }

  public int getWaitlistPosition() {
    return waitlistPosition;
  }

  public void setWaitlistPosition(int waitlistPosition) {
    this.waitlistPosition = waitlistPosition;
  }

  public Profile getSeller() {
    return seller;
  }

  public void setSeller(Profile seller) {
    this.seller = seller;
  }

  @NonNull
  public Date getTimestamp() {
    return timestamp;
  }

  @NonNull
  public Profile getCommissioner() {
    return commissioner;
  }

  public void setCommissioner(@NonNull Profile commissioner) {
    this.commissioner = commissioner;
  }

  public Long getProduct() {
    return product;
  }

  public void setProduct(Long product) {
    this.product = product;
  }

}
