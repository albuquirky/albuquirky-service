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
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import org.springframework.lang.NonNull;

/**
 * This is the {@code Profile} entity class which declares which attributes are needed for each
 * profile which includes, {@link Profile#id}, {@link Profile#username}, password, email and OAuth.
 * The profile image and address are both not required. Profile is on the OneToMany side from
 * {@link Commission} which has lists of commission requests and commissions selling. Also
 * {@link Product} has a list of {@link Profile#products}.
 */
@Entity
public class Profile {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "profile_id", nullable = false, updatable = false)
  private Long id;

  @NonNull
  @Column(unique = true, nullable = false)
  private String username;

  @NonNull
  @Column(unique = true, nullable = false)
  private String email;

  @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
  private ProfilePicture image;

  private String address;

  @JsonIgnore
  @NonNull
  @Column(unique = true, nullable = false, updatable = false)
  private String oauth;

  @NonNull
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "buyer",
      cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
  @OrderBy("placedDate DESC")
  @JsonIgnore
  private final List<Order> ordersBought = new LinkedList<>();

  @NonNull
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "seller",
      cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
  @OrderBy("placedDate DESC")
  @JsonIgnore
  private final List<Order> ordersSold = new LinkedList<>();

  @NonNull
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "profile",
      cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH})
  @OrderBy("name DESC")
  @JsonIgnore
  private final List<Product> products = new LinkedList<>();

  @NonNull
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "seller", cascade = {CascadeType.ALL})
  @OrderBy("waitlistPosition ASC")
  @JsonIgnore
  private final List<Commission> commissionsSelling = new LinkedList<>();

  @NonNull
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "commissioner", cascade = {CascadeType.ALL})
  @OrderBy("waitlistPosition ASC")
  @JsonIgnore
  private final List<Commission> commissionsRequested = new LinkedList<>();

  /**
   * Returns the {@link Profile#id}
   * @return The ID for the profile.
   */
  public Long getId() {
    return id;
  }

  /**
   * Returns the {@link Profile#username}
   * @return The username for the profile.
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
   * @return The email for the profile.
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
   * Gets the {@link ProfilePicture} for the profile.
   * @return The profile picture as a {@link ProfilePicture} object.
   */
  public ProfilePicture getImage() {
    return image;
  }

  /**
   * Sets the {@link ProfilePicture} for the profile.
   * @param image The new {@link ProfilePicture}.
   */
  public void setImage(ProfilePicture image) {
    this.image = image;
  }

  /**
   * Returns the {@link Profile#address}
   * @return The address for the profile.
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
   * @return The OAuth for the profile.
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
   * Gets a list of {@link Order} objects placed by the profile.
   * @return List of orders placed.
   */
  @NonNull
  public List<Order> getOrdersBought() {
    return ordersBought;
  }

  /**
   * Gets a list of {@link Order} objects sold by the profile.
   * @return List of orders sold.
   */
  @NonNull
  public List<Order> getOrdersSold() {
    return ordersSold;
  }

  /**
   * Gets a list of {@link Product} objects sold by the user.
   * @return List of products
   */
  @NonNull
  public List<Product> getProducts() {
    return products;
  }

  /**
   * Gets the {@link Profile} waitlist.
   * @return List of commissions selling
   */
  @NonNull
  public List<Commission> getCommissionsSelling() {
    return commissionsSelling;
  }

  /**
   * Gets the commissions requested by the {@link Profile}.
   * @return List of commissions requested
   */
  @NonNull
  public List<Commission> getCommissionsRequested() {
    return commissionsRequested;
  }

}
