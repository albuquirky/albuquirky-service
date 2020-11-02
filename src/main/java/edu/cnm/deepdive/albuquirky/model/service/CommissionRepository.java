package edu.cnm.deepdive.albuquirky.model.service;

import edu.cnm.deepdive.albuquirky.model.entity.Commission;
import edu.cnm.deepdive.albuquirky.model.entity.Profile;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommissionRepository extends JpaRepository<Commission, Long> {

  Optional<Commission> getCommissionsBySeller(Profile seller);


  Optional<Commission> getWaitlist(Profile buyer);

}
