package edu.cnm.deepdive.albuquirky.model.entity;

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
  @Column(nullable = false)
  private String password;

  @NonNull
  @Column(unique = true, nullable = false)
  private String email;

  @Column(unique = true)
  private String image;

  private String address;

  @NonNull
  @Column(unique = true, nullable = false, updatable = false)
  private String oauth;

  @NonNull
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "id",
      cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
  @OrderBy("placedDate DESC")
  private final List<Order> orders = new LinkedList<>();

  @NonNull
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "id",
      cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE, CascadeType.REFRESH})
  @OrderBy("name DESC")
  private final List<Product> products = new LinkedList<>();

  @NonNull
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "id", cascade = {CascadeType.ALL})
  @OrderBy("waitlistPosition ASC")
  private final List<Commission> commissionsSelling = new LinkedList<>();

  @NonNull
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "id", cascade = {CascadeType.ALL})
  @OrderBy("waitlistPosition ASC")
  private final List<Commission> commissionsRequested = new LinkedList<>();

  public Long getId() {
    return id;
  }

  @NonNull
  public String getUsername() {
    return username;
  }

  public void setUsername(@NonNull String username) {
    this.username = username;
  }

  @NonNull
  public String getPassword() {
    return password;
  }

  public void setPassword(@NonNull String password) {
    this.password = password;
  }

  @NonNull
  public String getEmail() {
    return email;
  }

  public void setEmail(@NonNull String email) {
    this.email = email;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @NonNull
  public String getOauth() {
    return oauth;
  }

  public void setOauth(@NonNull String oauth) {
    this.oauth = oauth;
  }

  @NonNull
  public List<Order> getOrders() {
    return orders;
  }

  @NonNull
  public List<Product> getProducts() {
    return products;
  }

  @NonNull
  public List<Commission> getCommissionsSelling() {
    return commissionsSelling;
  }

  @NonNull
  public List<Commission> getCommissionsRequested() {
    return commissionsRequested;
  }

}
