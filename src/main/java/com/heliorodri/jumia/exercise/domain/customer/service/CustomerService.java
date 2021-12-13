package com.heliorodri.jumia.exercise.domain.customer.service;

import com.heliorodri.jumia.exercise.api.v1.customer.dto.CustomerDTO;
import com.heliorodri.jumia.exercise.domain.customer.converter.CustomerConverter;
import com.heliorodri.jumia.exercise.domain.customer.repository.CustomerRepository;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class CustomerService {

  private final CustomerRepository repository;
  private final CustomerConverter converter;

  public List<CustomerDTO> findAll(String country, Boolean validNumber) {
    return converter.convertCustomersToDtos(repository.findAll()).stream()
        .filter(customerDTO -> customerDTO.getCountry().equals(country) || !StringUtils.hasText(country))
        .filter(customerDTO -> customerDTO.getValidNumber() == validNumber || validNumber == null)
        .sorted(Comparator.comparing(CustomerDTO::getName)
                    .thenComparing(CustomerDTO::getCountry)
                    .thenComparing(CustomerDTO::getNumber))
        .collect(Collectors.toList());
  }

}
