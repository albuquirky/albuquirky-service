package edu.cnm.deepdive.albuquirky.model.dao;

import edu.cnm.deepdive.albuquirky.model.entity.Commission;
import edu.cnm.deepdive.albuquirky.model.entity.Profile;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TODO edit this doc
 * The CommissionRepository interface gets a list of {@link Commission} all by seller.
 */
public interface CommissionRepository extends JpaRepository<Commission, Long> {

  List<Commission> getAllBySeller(Profile seller);


//  List<Commission> getWaitlist(Profile buyer);

}
