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
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "product_id", nullable = false, updatable = false)
  private Product product;

  @NonNull
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date created;

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
  public Product getProduct() {
    return product;
  }

  public void setProduct(@NonNull Product product) {
    this.product = product;
  }

  @NonNull
  public Date getCreated() {
    return created;
  }

}
