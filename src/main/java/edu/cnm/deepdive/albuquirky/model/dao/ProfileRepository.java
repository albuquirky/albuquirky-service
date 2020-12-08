package edu.cnm.deepdive.albuquirky.model.dao;

import edu.cnm.deepdive.albuquirky.model.entity.Profile;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The ProfileRepository interface gives an optional {@link Profile} by username and
 * an optional {@link Profile} by OAuth.
 */
public interface ProfileRepository extends JpaRepository<Profile, Long> {

  /**
   * The findFirstByUsername query finds a {@link Profile} by name.
   * @param name The username of the profile.
   * @return A {@link Profile} with the selected name.
   */
  Optional<Profile> findFirstByUsername(String name);

  /**
   * The findFirstByOauth query finds a {@link Profile} by OAuth.
   * @param oauth The authorization key for a profile.
   * @return A {@link Profile} with the selected OAuth key.
   */
  Optional<Profile> findFirstByOauth(String oauth);

}
