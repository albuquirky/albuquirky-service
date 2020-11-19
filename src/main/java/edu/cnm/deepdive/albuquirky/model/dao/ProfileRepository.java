package edu.cnm.deepdive.albuquirky.model.dao;

import edu.cnm.deepdive.albuquirky.model.entity.Profile;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The ProfileRepository interface gives an optional {@link Profile} by username and
 * an optional {@link Profile} by oauth.
 */
public interface ProfileRepository extends JpaRepository<Profile, Long> {

  /**
   * The findFirstByUsername query finds a {@link Profile} by name
   * @param name username of profile
   * @return a {@link Profile} with the selected name
   */
  Optional<Profile> findFirstByUsername(String name);

  /**
   * The findFirstByOauth query finds a {@link Profile} by uauth
   * @param oauth authorization key for a profile
   * @return a {@link Profile} with the selected oauth key
   */
  Optional<Profile> findFirstByOauth(String oauth);

}
