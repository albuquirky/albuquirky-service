package edu.cnm.deepdive.albuquirky.model.dao;

import edu.cnm.deepdive.albuquirky.model.entity.Profile;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

  Optional<Profile> findFirstByUsername(String name);

  Optional<Profile> findFirstByOauth(String oauth);

}
