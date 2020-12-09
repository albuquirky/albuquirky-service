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

/**
 * This is the {@link Image} entity class, which declares the attributes for the image. Included
 * are the image ID, image file name, the image description (not required), the {@link Product}
 * ID, and a created timestamp. The {@link Product} ID is annotated by a @ManyToOne.
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
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "product_id", nullable = false, updatable = false)
  private Product product;

  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date created;

  /**
   * Gets {@link Image#id}
   * @return id
   */
  public Long getId() {
    return id;
  }

  /**
   * Gets the filename for the image.
   * @return The image filename.
   */
  @NonNull
  public String getImageFileName() {
    return imageFileName;
  }

  /**
   * Sets {@link Image#imageFileName}
   * @param imageFileName- String
   */
  public void setImageFileName(@NonNull String imageFileName) {
    this.imageFileName = imageFileName;
  }

  /**
   * Gets the image description.
   * @return The image description.
   */
  public String getImageDescription() {
    return imageDescription;
  }

  /**
   * Sets {@link Image#imageDescription}
   * @param imageDescription- String
   */
  public void setImageDescription(String imageDescription) {
    this.imageDescription = imageDescription;
  }

  /**
   * Gets the {@link Product} associated with the image.
   * @return The {@link Product} for the image.
   */
  @NonNull
  public Product getProduct() {
    return product;
  }

  /**
   * Sets {@link Product} id
   * @param product- Product id
   */
  public void setProduct(@NonNull Product product) {
    this.product = product;
  }

  /**
   * Gets the creation timestamp for the image.
   * @return The image's creation timestamp.
   */
  @NonNull
  public Date getCreated() {
    return created;
  }

}
