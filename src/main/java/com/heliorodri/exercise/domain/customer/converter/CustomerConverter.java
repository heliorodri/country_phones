package com.heliorodri.exercise.domain.customer.converter;

import com.heliorodri.exercise.domain.customer.Customer;
import com.heliorodri.exercise.domain.customer.utils.PhoneConstants;
import com.heliorodri.exercise.api.v1.customer.dto.CustomerDTO;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter {

  public List<CustomerDTO> convertCustomersToDtos(List<Customer> customers) {
    return customers.stream().map(this::convertCustomerToDTO).collect(Collectors.toList());
  }

  private CustomerDTO convertCustomerToDTO(Customer customer) {
    return CustomerDTO.builder()
        .name(customer.getName())
        .country(getCountry(customer.getPhone()))
        .validNumber(isValidNumber(customer.getPhone()))
        .countryCode(getCountryCode(customer.getPhone()))
        .number(getNumber(customer.getPhone()))
        .build();
  }

  private String getCountryCode(String phone) {
     Matcher code = Pattern.compile("\\(([^)]+)\\)").matcher(phone);
     return code.find() ? code.group(1).trim() : "";
  }

  private String getNumber(String phone) {
    Matcher number = Pattern.compile("\\)(.*)").matcher(phone);
    return number.find() ? number.group(1).trim() : "";
  }

  private Boolean isValidNumber(String phone) {
    switch (getCountryCode(phone)) {
      case PhoneConstants.CODE_CAMEROON: return Pattern.compile(PhoneConstants.REGEX_CAMEROON).matcher(phone).find();
      case PhoneConstants.CODE_ETHIOPIA: return Pattern.compile(PhoneConstants.REGEX_ETHIOPIA).matcher(phone).find();
      case PhoneConstants.CODE_MOROCCO: return Pattern.compile(PhoneConstants.REGEX_MOROCCO).matcher(phone).find();
      case PhoneConstants.CODE_MOZAMBIQUE: return Pattern.compile(PhoneConstants.REGEX_MOZAMBIQUE).matcher(phone).find();
      case PhoneConstants.CODE_UGANDA: return Pattern.compile(PhoneConstants.REGEX_UGANDA).matcher(phone).find();

      default: return false;
    }
  }

  private String getCountry(String phone) {
    switch (getCountryCode(phone)) {
      case PhoneConstants.CODE_CAMEROON: return PhoneConstants.CAMEROON;
      case PhoneConstants.CODE_ETHIOPIA: return PhoneConstants.ETHIOPIA;
      case PhoneConstants.CODE_MOROCCO: return PhoneConstants.MOROCCO;
      case PhoneConstants.CODE_MOZAMBIQUE: return PhoneConstants.MOZAMBIQUE;
      case PhoneConstants.CODE_UGANDA: return PhoneConstants.UGANDA;

      default: return "";
    }
  }

}
