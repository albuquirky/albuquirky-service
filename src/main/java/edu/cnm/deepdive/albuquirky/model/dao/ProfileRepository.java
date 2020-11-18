package edu.cnm.deepdive.albuquirky.model.dao;

import edu.cnm.deepdive.albuquirky.model.entity.Profile;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The ProfileRepository interface gives an optional {@link Profile} by username and
 * an optional {@link Profile} by oauth.
 */
public interface ProfileRepository extends JpaRepository<Profile, Long> {

  Optional<Profile> findFirstByUsername(String name);

  Optional<Profile> findFirstByOauth(String oauth);

}
