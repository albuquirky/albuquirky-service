package edu.cnm.deepdive.albuquirky.service;

import edu.cnm.deepdive.albuquirky.model.dao.CommissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import edu.cnm.deepdive.albuquirky.model.entity.Commission;
import edu.cnm.deepdive.albuquirky.model.entity.Product;
import edu.cnm.deepdive.albuquirky.model.entity.Profile;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommissionService {

  private final CommissionRepository commissionRepository;

  @Autowired
  public CommissionService(CommissionRepository commissionRepository) {
    this.commissionRepository = commissionRepository;
  }

  public Commission save(Commission commission, Profile profile) {
    commission.setProfile(profile);
    return commissionRepository.save(commission);
  }

  public List<Commission> getBySeller(Profile profile) {
    return commissionRepository.getAllBySeller(profile);
  }
}
