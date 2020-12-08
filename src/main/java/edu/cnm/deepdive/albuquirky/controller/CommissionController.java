package edu.cnm.deepdive.albuquirky.controller;

import edu.cnm.deepdive.albuquirky.model.entity.Commission;
import edu.cnm.deepdive.albuquirky.model.entity.Profile;
import edu.cnm.deepdive.albuquirky.service.CommissionService;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The CommissionController class is the {@code RestController} that maps the endpoints of
 * "/commissions" for the communication between the server-side and client-side for
 * {@link Commission}.
 */
@RestController
@RequestMapping("/commissions")
@ExposesResourceFor(Commission.class)
public class CommissionController {

  private final CommissionService commissionService;

  /**
   * Constructs the instance of CommissionService object
   * @param commissionService The {@link CommissionService} to initialize.
   */
  public CommissionController(CommissionService commissionService) {
    this.commissionService = commissionService;
  }

  /**
   * The Get method which returns a list of commission from seller
   * @param auth The authorization for the user.
   * @return A {@code List} of {@link Commission} objects representing the user's waitlist.
   */
  @GetMapping(value = "/seller", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Commission> getCommissionsWhereSeller(Authentication auth) {
    return commissionService.getWaitlist(getAuthProfile(auth));
  }

  /**
   * The Get method which returns a list of commission from commissioner(buyer)
   * @param auth The authorization for the user.
   * @return A {@code List} of {@link Commission} objects representing those the user commissioned.
   */
  @GetMapping(value = "/buyer", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Commission> getCommissionsWhereCommissioner(Authentication auth) {
    return commissionService.getByCommissioner(getAuthProfile(auth));
  }

  /**
   * The Post method for creating a new commission.
   * @param commission The {@link Commission} to be created.
   * @param auth The authorization for the user.
   * @return The {@link Commission} object that was created.
   */
  @PostMapping(
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
  public Commission post(@RequestBody Commission commission, Authentication auth) {
    commission.setCommissioner(getAuthProfile(auth));
    return commissionService.save(commission);
  }

  /**
   * The Get method that returns the commission by commission id
   * @param commissionId The ID of the {@link Commission}.
   * @return The matching {@link Commission} object.
   */
  @GetMapping(value = "/{commissionId:\\d+}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Commission getCommission(@PathVariable long commissionId) {
    return commissionService.get(commissionId).orElseThrow(NoSuchElementException::new);
  }

  /**
   * The Delete method that returns a response entity indicating success or failure.
   * @param commissionId The ID of the {@link Commission} to be deleted.
   * @return A {@code ResponseEntity} indicating the status of the delete attempt.
   */
  @DeleteMapping(value = "/{commissionId:\\d+}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Long> deleteCommission(
      @PathVariable long commissionId, Commission commission, Profile profile) {
    commissionService.remove(commission, profile);
    return commissionService.get(commissionId).isEmpty()
        ? new ResponseEntity<>(commissionId, HttpStatus.OK)
        : new ResponseEntity<>(commissionId, HttpStatus.NOT_FOUND);
  }

  /**
   * The Get method that returns the commission request by commission id
   * @param commissionId The ID of the {@link Commission}.
   * @return The matching {@link Commission} object.
   */
  @GetMapping(value = "/{commissionId:\\d+}/commission-request",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public String getCommissionRequest(@PathVariable long commissionId) {
    Commission commission = getCommission(commissionId);
    return commission.getCommissionRequest();
  }

  /**
   * The Put method which allows update to the commission request
   * @param request The String content of the {@link Commission} request.
   * @param commissionId The ID of the {@link Commission}.
   * @return The updated {@link Commission} request field content.
   */
  @PutMapping(value = "/{commissionId:\\d+}/commission-request",
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
