package edu.cnm.deepdive.albuquirky.model.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import org.springframework.lang.NonNull;

/**
 * This is the Commission Entity class, which declares the attributes needed for each commission
 * which includes, the commission ID, the commission request, waitlist position, the seller ID,
 * a timestamp, the commission ID and the product ID. Both seller ID and commissioner ID are annotated
 * by ManyToOne coming from {@link Profile}. Commissions accepted by the commissioner become products.
 */
@Entity
@Table(indexes = {@Index(columnList = "waitlistPosition")})
public class Commission {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "commission_id", nullable = false, updatable = false)
  private Long id;

  @NonNull
  @Column(length = 200)
  private String commissionRequest;

  @NonNull
  @Column()
  private int waitlistPosition;

  @NonNull
  @ManyToOne
  @JoinColumn(name = "profile_id", nullable = false, updatable = false)
  private Profile profile;

  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date timestamp;

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

  @NonNull
  public Profile getProfile() {
    return profile;
  }

  @NonNull
  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(@NonNull Date timestamp) {
    this.timestamp = timestamp;
  }
}
