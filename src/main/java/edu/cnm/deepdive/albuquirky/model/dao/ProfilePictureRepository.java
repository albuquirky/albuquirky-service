package edu.cnm.deepdive.albuquirky.model.dao;

import edu.cnm.deepdive.albuquirky.model.entity.Profile;
import edu.cnm.deepdive.albuquirky.model.entity.ProfilePicture;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The ProfilePictureRepository contains methods used to query the database for items in the
 * {@link ProfilePicture} table.
 */
public interface ProfilePictureRepository extends JpaRepository<ProfilePicture, Long> {

  /**
   * Retrieves a user's profile picture.
   * @param id The ID of the {@link ProfilePicture}.
   * @param user The user {@link Profile}.
   * @return An {@code Optional} containing the matching {@link ProfilePicture}, if any.
   */
  Optional<ProfilePicture> findFirstByIdAndUser(long id, Profile user);

}
