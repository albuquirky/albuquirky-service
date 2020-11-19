package edu.cnm.deepdive.albuquirky.service;

import edu.cnm.deepdive.albuquirky.model.dao.CommissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class CommissionService {

  private final CommissionRepository commissionRepository;

  @Autowired
  public CommissionService(CommissionRepository commissionRepository) {
    this.commissionRepository = commissionRepository;
  }

}
