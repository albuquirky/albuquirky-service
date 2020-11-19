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

@Service
public class CommissionService {

  private final CommissionRepository commissionRepository;
  private final ProfileRepository profileRepository;

  @Autowired
  public CommissionService(CommissionRepository commissionRepository,
      ProfileRepository profileRepository) {
    this.commissionRepository = commissionRepository;
    this.profileRepository = profileRepository;
  }

  public Commission save(Commission commission) {
    return commissionRepository.save(commission);
  }

  public Commission save(Commission commission, Profile profile) {
    return profileRepository.findById(commission.getSeller().getId())
        .map((seller) -> {
          commission.setSeller(seller);
          commission.setCommissioner(profile);
          return commissionRepository.save(commission);
        })
        .orElseThrow(NoSuchElementException::new);
  }
// TODO Add a remove method using specified business logic

  public Optional<Commission> get(long id) {
    return commissionRepository.findById(id);
  }

  public List<Commission> getBySeller(Profile seller) {
    return commissionRepository.getAllBySellerOrderByTimestamp(seller);
  }

  public List<Commission> getWaitlist(Profile seller) {
    return commissionRepository.findBySellerAndWaitlistPositionGreaterThanOrderByWaitlistPosition(seller, 0);
  }

  public List<Commission> getByCommissioner(Profile commissioner) {
    return commissionRepository.findByCommissionerOrderByTimestamp(commissioner);
  }

}
