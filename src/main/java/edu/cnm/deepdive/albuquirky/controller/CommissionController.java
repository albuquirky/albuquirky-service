package edu.cnm.deepdive.albuquirky.controller;

import edu.cnm.deepdive.albuquirky.model.dao.CommissionRepository;
import edu.cnm.deepdive.albuquirky.model.entity.Commission;
import edu.cnm.deepdive.albuquirky.model.entity.Profile;
import edu.cnm.deepdive.albuquirky.service.CommissionService;
import edu.cnm.deepdive.albuquirky.service.ProductService;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/commissions")
@ExposesResourceFor(Commission.class)
public class CommissionController {

  private final CommissionService commissionService;

  public CommissionController(CommissionService commissionService) {
    this.commissionService = commissionService;
  }

  @GetMapping(value = "/seller", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Commission> getCommissionsWhereSeller(Authentication auth) {
    return commissionService.getWaitlist(getAuthProfile(auth));
  }

  @GetMapping(value = "/buyer", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Commission> getCommissionsWhereCommissioner(Authentication auth) {
    return commissionService.getByCommissioner(getAuthProfile(auth));
  }

  // TODO: POST

  @GetMapping(value = "/{commissionId:\\d+}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Commission getCommission(@PathVariable long commissionId) {
    return commissionService.get(commissionId).orElseThrow(NoSuchElementException::new);
  }

  // TODO: DELETE

  @GetMapping(value = "/{commissionId:\\d+}/commission_request",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public String getCommissionRequest(@PathVariable long commissionId) {
    Commission commission = getCommission(commissionId);
    return commission.getCommissionRequest();
  }

  @PutMapping(value = "/{commissionId:\\d+}/commission_request",
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
  public String updateCommissionRequest(
      @RequestBody String request, @PathVariable long commissionId) {
    Commission commission = getCommission(commissionId);
    commission.setCommissionRequest(request);
    return commissionService.save(commission).getCommissionRequest();
  }

  private static Profile getAuthProfile(Authentication auth) {
    return (Profile) auth.getPrincipal();
  }

}
