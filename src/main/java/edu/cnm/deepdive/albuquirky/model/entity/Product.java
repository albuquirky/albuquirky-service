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

/**
 * This is the {@code Product} entity class, which declares the attributes needed for products which
 * includes, the product id, username, product description (not required), {@link Product#name},
 * {@link Product#price}, {@link Product#stock}, {@link Product#postedDate} and the {@link Profile}
 * id which is annotated by a @ManyToOne. Two attributes which are annotated by a @OneToMany are
 * {@link Product#productImages} which gives a list of {@link Image} for a {@link Product} and order
 * items which gives a list of items of {@link ProductOnOrder}.
 */
@Entity
public class Product {

  /**
   * The primary key of the class, the product id
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "product_id", nullable = false, updatable = false)
  private Long id;

  /**
   * The name of the product
   */
  @NonNull
  @Column(name = "product_name", nullable = false)
  private String name;

  /**
   * The product description
   */
  private String description;

  /**
   * The price of the product
   */
  @NonNull
  @Column(nullable = false)
  private int price;

  /**
   * The stock of the product
   */
  @NonNull
  @Column(nullable = false)
  private int stock;

  /**
   * Date timestamp of the product's posted date
   */
  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false)
  private Date postedDate;

  /**
   * The ManyToOne side of the relationship between {@link Profile} and {@link Product}
   */
  @NonNull
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "profile_id", nullable = false, updatable = false)
  private Profile profile;

  /**
   * The OneToMany side of the relationship between {@link Image} and {@link Product} which shows
   * a list of product images
   */
  @NonNull
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "product",
      cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.MERGE})
  @OrderBy("created DESC")
  private final List<Image> productImages = new LinkedList<>();

  /**
   * The OneToMany side of the relationship between {@link ProductOnOrder} and {@link Product} which
   * gives a list of order items
   */
  @NonNull
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "order",
      cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
  @OrderBy("itemQuantity DESC")
  private final List<ProductOnOrder> orderItems = new LinkedList<>();

  /**
   * Gets the {@link Product#id}
   * @return id
   */
  public Long getId() {
    return id;
  }

  /**
   * Gets the {@link Product#name}
   * @return name
   */
  @NonNull
  public String getName() {
    return name;
  }

  /**
   * Sets {@link Product#name}
   * @param name- String
   */
  public void setName(@NonNull String name) {
    this.name = name;
  }

  /**
   * Returns the {@link Product#description}
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets {@link Product#description}
   * @param description- String
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Returns the {@link Product#price}
   */
  public int getPrice() {
    return price;
  }

  /**
   * Sets {@link Product#price}
   * @param price- int
   */
  public void setPrice(int price) {
    this.price = price;
  }

  /**
   * Returns the {@link Product#stock}
   */
  public int getStock() {
    return stock;
  }

  /**
   * Sets {@link Product#stock}
   * @param stock- int
   */
  public void setStock(int stock) {
    this.stock = stock;
  }

  /**
   * Returns the {@link Product#postedDate}
   */
  @NonNull
  public Date getPostedDate() {
    return postedDate;
  }

  /**
   * Returns the {@link Profile}
   */
  @NonNull
  public Profile getProfile() {
    return profile;
  }

  /**
   * Sets {@link Profile}
   * @param profile- Profile
   */
  public void setProfile(@NonNull Profile profile) {
    this.profile = profile;
  }

  /**
   *
   * @return List of productImages
   */
  @NonNull
  public List<Image> getProductImages() {
    return productImages;
  }

  /**
   *
   * @return List of orderItems
   */
  @NonNull
  public List<ProductOnOrder> getOrderItems() {
    return orderItems;
  }

}
