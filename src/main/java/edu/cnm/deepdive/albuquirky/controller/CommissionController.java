package edu.cnm.deepdive.albuquirky.controller;

import edu.cnm.deepdive.albuquirky.model.dao.CommissionRepository;
import edu.cnm.deepdive.albuquirky.service.CommissionService;
import edu.cnm.deepdive.albuquirky.service.ProductService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommissionController {

  private final CommissionService commissionService;

  public CommissionController(CommissionService commissionService) {
    this.commissionService = commissionService;

  }
}
