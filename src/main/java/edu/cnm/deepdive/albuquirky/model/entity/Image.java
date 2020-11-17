package edu.cnm.deepdive.albuquirky.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.lang.NonNull;

/**
 * This is the Image Entity class, which declares the attributes for the image. Included are the image
 * ID, image file name, the image description (not required), product ID and a created timestamp.
 * {@link Product} ID is annotated by a ManyToOne.
 */
@Entity
public class Image {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "image_id", nullable = false, updatable = false)
  private Long id;

  @NonNull
  @Column(name = "image_file_name", nullable = false, unique = true)
  private String imageFileName;

  @Column(name = "image_description")
  private String imageDescription;

  @NonNull
  @Column(name = "profile_id", nullable = false, updatable = false)
  private Long profile;

  public Long getId() {
    return id;
  }

  @NonNull
  public String getImageFileName() {
    return imageFileName;
  }

  public void setImageFileName(@NonNull String imageFileName) {
    this.imageFileName = imageFileName;
  }

  public String getImageDescription() {
    return imageDescription;
  }

  public void setImageDescription(String imageDescription) {
    this.imageDescription = imageDescription;
  }

  @NonNull
  public Long getProfile() {
    return profile;
  }

}
