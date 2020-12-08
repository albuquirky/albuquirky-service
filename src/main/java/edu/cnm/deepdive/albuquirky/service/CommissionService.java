package edu.cnm.deepdive.albuquirky.service;

import edu.cnm.deepdive.albuquirky.model.dao.CommissionRepository;
import edu.cnm.deepdive.albuquirky.model.dao.ProfileRepository;
import edu.cnm.deepdive.albuquirky.model.entity.Commission;
import edu.cnm.deepdive.albuquirky.model.entity.Profile;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class handles all of the business logic for getting, putting, posting, and deleting items
 * from the {@link Commission} on behalf of the {@link CommissionService} class, using methods from
 *  the {@link CommissionRepository} interface.
 */
@Service
public class CommissionService {

  /**
   * Field to store the instance of {@link CommissionRepository}.
   */
  private final CommissionRepository commissionRepository;

  /**
   * Field to store the instance of {@link ProfileRepository}.
   */
  private final ProfileRepository profileRepository;

  /**
   * Constructor for the instances of {@link CommissionRepository} and {@link ProfileRepository}.
   *
   * @param commissionRepository An instance of {@link CommissionRepository} to be initialized.
   * @param profileRepository An instance of {@link ProfileRepository} to be initialized.
   */
  @Autowired
  public CommissionService(CommissionRepository commissionRepository,
      ProfileRepository profileRepository) {
    this.commissionRepository = commissionRepository;
    this.profileRepository = profileRepository;
  }

  /**
   * Method to save an entire {@link Commission}.
   *
   * @param commission An instance of {@link Commission} to be saved.
   */
  public Commission save(Commission commission) {
    return commissionRepository.save(commission);
  }

  /**
   * Method to save the seller and commissioner on a {@link Commission}.
   *
   * @param commission An instance of {@link Commission}.
   * @param profile An instance of {@link Profile} representing the commissioned party.
   */
  public Commission save(Commission commission, Profile profile) {
    return profileRepository.findById(commission.getSeller().getId())
        .map((seller) -> {
          commission.setSeller(seller);
          commission.setCommissioner(profile);
          return commissionRepository.save(commission);
        })
        .orElseThrow(NoSuchElementException::new);
  }

  /**
   * Method to delete a commission if the user is either the commissioner or the seller.
   *
   * @param commission The {@link Commission} to be deleted.
   * @param profile The {@link {Profile} of the user attempting to delete the commission.
   * @return The Commission being deleted.
   */
  public Commission remove(Commission commission, Profile profile) {
    long commissionerId = commission.getCommissioner().getId();
    long sellerId = commission.getSeller().getId();
    if (commissionerId != profile.getId() || sellerId != profile.getId()) {
      // TODO throw an exception indicating access is forbidden
    } else {
      commissionRepository.delete(commission);
    }
    return commission;
  }

  /**
   * Method to return a commission that matches a specific ID.
   * @param id The ID of the {@link Commission} being requested.
   * @return An {@code Optional} containing the matching {@link Commission}.
   */
  public Optional<Commission> get(long id) {
    return commissionRepository.findById(id);
  }

  /**
   * Method to return a list of (@link Commission} by the commissioned party, ordered by timestamp.
   *
   * @param seller An instance of {@link Profile} representing the commissioned party.
   */
  public List<Commission> getBySeller(Profile seller) {
    return commissionRepository.getAllBySellerOrderByTimestamp(seller);
  }

  /**
   * Method to return a truncated list of {@link Commission} representing the user's waitlist.
   *
   * @param seller An instance of {@link Profile} representing the commissioned party.
   */
  public List<Commission> getWaitlist(Profile seller) {
    return commissionRepository.findBySellerAndWaitlistPositionGreaterThanOrderByWaitlistPosition(seller, 0);
  }

  /**
   * Method to return a list of {@link Commission} by the commissioning party, ordered by timestamp.
   *
   * @param commissioner An instance of {@link Profile} representing the commissioning party.
   */
  public List<Commission> getByCommissioner(Profile commissioner) {
    return commissionRepository.findByCommissionerOrderByTimestamp(commissioner);
  }

}
