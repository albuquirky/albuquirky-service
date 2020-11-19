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
 * This is the {@code Image} entity class, which declares the attributes for the image. Included are the image
 * id, image file name, the image description (not required), {@link Product} id and a created timestamp.
 * {@link Product} id is annotated by a @ManyToOne.
 */
@Entity
public class Image {

  /**
   * The Primary Key for the class, the image id
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "image_id", nullable = false, updatable = false)
  private Long id;
  /**
   * The image file name
   */
  @NonNull
  @Column(name = "image_file_name", nullable = false, unique = true)
  private String imageFileName;
  /**
   * The image description
   */
  @Column(name = "image_description")
  private String imageDescription;
  /**
   * The ManyToOne side of the relationship between {@link Product} and {@link Image} for the image
   * of a product.
   */
  @NonNull
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "product_id", nullable = false, updatable = false)
  private Product product;
  /**
   * Date timestamp of the created image
   */
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
   * Returns the {@link Image#imageFileName}
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
   * Returns the {@link Image#imageDescription}
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
   * Returns the {@link Product} id
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
   * Returns the {@link Image#created}
   */
  @NonNull
  public Date getCreated() {
    return created;
  }

}
