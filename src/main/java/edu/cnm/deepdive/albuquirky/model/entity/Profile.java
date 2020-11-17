package edu.cnm.deepdive.albuquirky.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.lang.NonNull;

/**
 * This is the Profile Entity class, which declares which attributes are needed for each profile which
 * includes, the profile ID, username, password, email and oauth. The profile image and address are both
 * not required. Profile is on the OneToMany side from {@link Commission} which has lists of commission
 * requests and commissions selling. Also {@link Product} has a list of Profile products.
 */
@Entity
public class Profile {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "profile_id", nullable = false, updatable = false)
  private Long id;

  @NonNull
  @Column(unique = true)
  private String username;

  @NonNull
  private String password;

  @NonNull
  @Column(unique = true)
  private String email;

  @Column(unique = true)
  private String image;

  private String address;

  @NonNull
  @Column(unique = true)
  private String oauth;

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
}
