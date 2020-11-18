package edu.cnm.deepdive.albuquirky.service;

import edu.cnm.deepdive.albuquirky.model.dao.CommissionRepository;
import edu.cnm.deepdive.albuquirky.model.dao.ProfileRepository;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import edu.cnm.deepdive.albuquirky.model.entity.Commission;
import edu.cnm.deepdive.albuquirky.model.entity.Profile;
import java.util.List;
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

  public List<Commission> getBySeller(Profile profile) {
    return commissionRepository.getAllBySeller(profile);
  }
}
