package edu.cnm.deepdive.albuquirky.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import org.springframework.lang.NonNull;

/**
 * This is the {@code Profile} entity class which declares which attributes are needed for each profile which
 * includes, {@link Profile#id}, {@link Profile#username}, password, email and oauth. The profile
 * image and address are both not required. Profile is on the OneToMany side from {@link Commission}
 * which has lists of commission requests and commissions selling. Also {@link Product} has a
 * list of {@link Profile#products}.
 *
 */
@Entity
public class Profile {

  /**
   * The Primary Key for the class, the profile id
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "profile_id", nullable = false, updatable = false)
  private Long id;
  /**
   * The profile's username
   */
  @NonNull
  @Column(unique = true, nullable = false)
  private String username;
  /**
   * The profile's email
   */
  @NonNull
  @Column(unique = true, nullable = false)
  private String email;
  /**
   * The profile's image
   */
  @Column(unique = true)
  private String image;
  /**
   * The profile's address
   */
  private String address;
  /**
   * The profile's OAuth key from Google Sign-in
   */
  @NonNull
  @Column(unique = true, nullable = false, updatable = false)
  private String oauth;
  /**
   * The OneToMany side of the relationship between {@link Order} and {@link Profile} for a list of
   * orders associated with a profile.
   */
  @NonNull
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "buyer",
      cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
  @OrderBy("placedDate DESC")
  @JsonIgnore
  private final List<Order> orders = new LinkedList<>();
  /**
   * The OneToMany side of the relationship between {@link Product} and {@link Profile} for a list
   * of products associated with a profile.
   */
  @NonNull
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "profile",
      cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH})
  @OrderBy("name DESC")
  @JsonIgnore
  private final List<Product> products = new LinkedList<>();
  /**
   * The OneToMany side of the relationship between {@link Commission} and {@link Profile} for a
   * list of commissions selling
   */
  @NonNull
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "seller", cascade = {CascadeType.ALL})
  @OrderBy("waitlistPosition ASC")
  @JsonIgnore
  private final List<Commission> commissionsSelling = new LinkedList<>();
  /**
   * The OneToMany side of the relationship between {@link Commission} and {@link Profile}
   */
  @NonNull
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "commissioner", cascade = {CascadeType.ALL})
  @OrderBy("waitlistPosition ASC")
  @JsonIgnore
  private final List<Commission> commissionsRequested = new LinkedList<>();

  /**
   * Returns the {@link Profile#id}
   */
  public Long getId() {
    return id;
  }

  /**
   * Returns the {@link Profile#username}
   */
  @NonNull
  public String getUsername() {
    return username;
  }

  /**
   * Sets the {@link Profile#username}
   * @param username String
   */
  public void setUsername(@NonNull String username) {
    this.username = username;
  }

  /**
   * Returns the {@link Profile#email}
   */
  @NonNull
  public String getEmail() {
    return email;
  }

  /**
   * Sets the email
   * @param email String
   */
  public void setEmail(@NonNull String email) {
    this.email = email;
  }

  /**
   *  Returns the {@link Profile#image}
   */
  public String getImage() {
    return image;
  }

  /**
   * Sets {@link Profile#image}
   * @param image String
   */
  public void setImage(String image) {
    this.image = image;
  }

  /**
   * Returns the {@link Profile#address}
   */
  public String getAddress() {
    return address;
  }

  /**
   * Sets the {@link Profile#address}
   * @param address String
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * Returns the Profile's OAuth 2.0
   */
  @NonNull
  public String getOauth() {
    return oauth;
  }

  /**
   * Sets the OAuth 2.0
   * @param oauth- String
   */
  public void setOauth(@NonNull String oauth) {
    this.oauth = oauth;
  }

  /**
   *
   * @return List of orders
   */
  @NonNull
  public List<Order> getOrders() {
    return orders;
  }

  /**
   *
   * @return List of products
   */
  @NonNull
  public List<Product> getProducts() {
    return products;
  }

  /**
   *
   * @return List of commissions selling
   */
  @NonNull
  public List<Commission> getCommissionsSelling() {
    return commissionsSelling;
  }

  /**
   *
   * @return List of commissions requested
   */
  @NonNull
  public List<Commission> getCommissionsRequested() {
    return commissionsRequested;
  }

}
