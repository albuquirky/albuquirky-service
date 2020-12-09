package edu.cnm.deepdive.albuquirky.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

/**
 *
 */
@Entity
public class ProfilePicture {

  @NonNull
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  @Column(nullable = false, updatable = false)
  private Long id;

  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date created;

  @NonNull
  @Column(nullable = false, updatable = false)
  private String name;

  @NonNull
  @Column(nullable = false, updatable = false)
  private String path;

  @NonNull
  @Column(nullable = false, updatable = false)
  private String contentType;

  @NonNull
  @OneToOne
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  @JsonIgnore
  private Profile user;

  /**
   * Gets the ID for the profile picture.
   * @return The ID.
   */
  @NonNull
  public Long getId() {
    return id;
  }

  /**
   * Gets the creation timestamp for the profile picture.
   * @return The profile picture creation timestamp.
   */
  @NonNull
  public Date getCreated() {
    return created;
  }

  /**
   * Sets the creation timestamp for the profile picture.
   * @param created The updated timestamp.
   */
  public void setCreated(@NonNull Date created) {
    this.created = created;
  }

  /**
   * The filename for the profile picture.
   * @return The profile picture's filename.
   */
  @NonNull
  public String getName() {
    return name;
  }

  /**
   * Sets the profile picture's filename.
   * @param name The updated filename.
   */
  public void setName(@NonNull String name) {
    this.name = name;
  }

  /**
   * Gets the file path for the profile picture.
   * @return The current file path.
   */
  @NonNull
  public String getPath() {
    return path;
  }

  /**
   * Sets the file path for the profile picture.
   * @param path The updated file path.
   */
  public void setPath(@NonNull String path) {
    this.path = path;
  }

  /**
   * Gets the file extension for the profile picture.
   * @return The current file extension.
   */
  @NonNull
  public String getContentType() {
    return contentType;
  }

  /**
   * Sets the file extension for the profile picture.
   * @param contentType The updated file extension.
   */
  public void setContentType(@NonNull String contentType) {
    this.contentType = contentType;
  }

  /**
   * Gets the associated {@link Profile} for the profile picture.
   * @return The {@link Profile} using this profile picture.
   */
  @NonNull
  public Profile getUser() {
    return user;
  }

  /**
   * Sets the associated {@link Profile} for the profile picture.
   * @param user The new {@link Profile} using this profile picture.
   */
  public void setUser(@NonNull Profile user) {
    this.user = user;
  }

}
