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
  @Column(nullable = false)
  private int price;

  @NonNull
  @Column(nullable = false)
  private int stock;

  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false)
  private Date postedDate;

  @NonNull
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "profile_id", nullable = false, updatable = false)
  private Profile profile;

  @NonNull
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "product_id",
      cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.MERGE})
  @OrderBy("created DESC")
  private final List<Image> productImages = new LinkedList<>();

  @NonNull
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "product_id",
      cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
  @OrderBy("itemQuantity DESC")
  private final List<OrderItem> orderItems = new LinkedList<>();

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

  @NonNull
  public Profile getProfile() {
    return profile;
  }

  public void setProfile(@NonNull Profile profile) {
    this.profile = profile;
  }

  @NonNull
  public List<Image> getProductImages() {
    return productImages;
  }

  @NonNull
  public List<OrderItem> getOrderItems() {
    return orderItems;
  }

}
