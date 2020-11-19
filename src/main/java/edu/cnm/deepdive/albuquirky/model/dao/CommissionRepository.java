package edu.cnm.deepdive.albuquirky.model.dao;

import edu.cnm.deepdive.albuquirky.model.entity.Commission;
import edu.cnm.deepdive.albuquirky.model.entity.Profile;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The CommissionRepository contains a list of methods used to query the database for items in the
 * {@link Commission} table.
 */
public interface CommissionRepository extends JpaRepository<Commission, Long> {

  /**
   * Method to get a list of {@link Commission} for a specific seller, ordered by timestamp.
   *
   * @param seller is an instance of {@link Profile} representing the commissioned party.
   */
  List<Commission> getAllBySellerOrderByTimestamp(Profile seller);

  /**
   * Method to get a list of {@link Commission} for a specific seller, where the waitlist position
   *  is greater than a specific {@code position} and ordered by their position in the waitlist.
   *
   * @param seller is an instance of {@link Profile} representing the commissioned party.
   * @param position is the int value to search for elements greater than.
   */
  List<Commission> findBySellerAndWaitlistPositionGreaterThanOrderByWaitlistPosition(Profile seller, int position);

  /**
   * Method to get a list of {@link Commission} submitted by a specific user, ordered by timestamp.
   *
   * @param commissioner is an instance of {@link Profile} representing the commissioning party.
   */
  List<Commission> findByCommissionerOrderByTimestamp(Profile commissioner);

}
