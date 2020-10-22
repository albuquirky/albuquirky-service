package edu.cnm.deepdive.albuquirky.model.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

@Entity
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "product_id", nullable = false, updatable = false)
  private Long id;

  @NonNull
  @Column(name = "product_name", nullable = false)
  private String name;

  private String description;

  @NonNull
  private int price;

  @NonNull
  private int stock;

  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date postedDate;

  @NonNull
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "profile_id", nullable = false, updatable = false)
  private Profile profile;

  public Long getId() {
    return id;
  }

  @NonNull
  public String getName() {
    return name;
  }

  public void setName(@NonNull String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  @NonNull
  public Date getPostedDate() {
    return postedDate;
  }

  public void setPostedDate(@NonNull Date postedDate) {
    this.postedDate = postedDate;
  }

  @NonNull
  public Profile getProfile() {
    return profile;
  }

}
