package com.heliorodri.exercise.api.v1.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Builder
@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerDTO {

  private String name;
  private String country;
  private Boolean validNumber;
  private String countryCode;
  private String number;

}
