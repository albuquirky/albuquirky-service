package edu.cnm.deepdive.albuquirky.model.service;

import edu.cnm.deepdive.albuquirky.model.entity.Product;
import edu.cnm.deepdive.albuquirky.model.entity.Profile;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Product, Long> {

  Optional<Profile> findByUserName(String name);

}
