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
   *
   * @return
   */
  @NonNull
  public Long getId() {
    return id;
  }

  /**
   *
   * @return
   */
  @NonNull
  public Date getCreated() {
    return created;
  }

  /**
   *
   * @param created
   */
  public void setCreated(@NonNull Date created) {
    this.created = created;
  }

  /**
   *
   * @return
   */
  @NonNull
  public String getName() {
    return name;
  }

  /**
   *
   * @param name
   */
  public void setName(@NonNull String name) {
    this.name = name;
  }

  /**
   *
   * @return
   */
  @NonNull
  public String getPath() {
    return path;
  }

  /**
   *
   * @param path
   */
  public void setPath(@NonNull String path) {
    this.path = path;
  }

  /**
   *
   * @return
   */
  @NonNull
  public String getContentType() {
    return contentType;
  }

  /**
   *
   * @param contentType
   */
  public void setContentType(@NonNull String contentType) {
    this.contentType = contentType;
  }

  /**
   *
   * @return
   */
  @NonNull
  public Profile getUser() {
    return user;
  }

  /**
   *
   * @param user
   */
  public void setUser(@NonNull Profile user) {
    this.user = user;
  }

}
