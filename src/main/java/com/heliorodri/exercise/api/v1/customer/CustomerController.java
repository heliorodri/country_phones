package com.heliorodri.exercise.api.v1.customer;

import com.heliorodri.exercise.api.v1.customer.dto.CustomerDTO;
import com.heliorodri.exercise.domain.customer.service.CustomerService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerService service;

  @GetMapping
  @CrossOrigin(origins = "http://localhost:3000")
  public ResponseEntity<List<CustomerDTO>> findAll(@RequestParam(required = false) String country,
                                                   @RequestParam(required = false) Boolean validNumber) {
    return ResponseEntity.ok(service.findAll(country, validNumber));
  }

}
