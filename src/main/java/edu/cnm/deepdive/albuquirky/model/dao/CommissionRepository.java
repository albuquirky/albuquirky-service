package edu.cnm.deepdive.albuquirky.model.dao;

import edu.cnm.deepdive.albuquirky.model.entity.Commission;
import edu.cnm.deepdive.albuquirky.model.entity.Profile;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommissionRepository extends JpaRepository<Commission, Long> {

  List<Commission> getAllBySellerOrderByTimestamp(Profile seller);

  List<Commission> findBySellerAndWaitlistPositionGreaterThanOrderByWaitlistPosition(Profile seller, int position);

  List<Commission> findByCommissionerOrderByTimestamp(Profile commissioner);

}
