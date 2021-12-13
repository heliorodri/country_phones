package com.heliorodri.jumia.exercise.domain.customer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.heliorodri.jumia.exercise.api.v1.customer.dto.CustomerDTO;
import com.heliorodri.jumia.exercise.domain.customer.Customer;
import com.heliorodri.jumia.exercise.domain.customer.converter.CustomerConverter;
import com.heliorodri.jumia.exercise.domain.customer.repository.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerServiceTest {

  private static final String NAME = "test";

  private static final String PHONE_MOROCCO = "(212) 698054317";
  private static final String COUNTRY_MOROCCO = "MOROCCO";
  private static final String CODE_MOROCCO = "212";
  private static final String NUMBER_MOROCCO = "698054317";

  private static final String PHONE_CAMEROON = "(237) 672263569";
  private static final String COUNTRY_CAMEROON = "CAMEROON";
  private static final String CODE_CAMEROON = "237";
  private static final String NUMBER_CAMEROON = "672263569";

  private CustomerRepository repository;
  private CustomerService service;

  @BeforeEach
  public void setUp() {
    CustomerConverter converter = new CustomerConverter();
    repository = mock(CustomerRepository.class);
    service = new CustomerService(repository, converter);
  }

  @Test
  public void itShouldListAllRegistersWithSuccess(){
    List<Customer> mockDBRegisters = List.of(buildCustomerCameroon(), buildCustomerMorocco());
    when(repository.findAll()).thenReturn(mockDBRegisters);

    List<CustomerDTO> actual = service.findAll(null, null);

    assertNotNull(actual);
    assertEquals(NAME, actual.get(0).getName());
    assertEquals(COUNTRY_CAMEROON, actual.get(0).getCountry());
    assertTrue(actual.get(0).getValidNumber());
    assertEquals(CODE_CAMEROON, actual.get(0).getCountryCode());
    assertEquals(NUMBER_CAMEROON, actual.get(0).getNumber());

    assertEquals(NAME, actual.get(1).getName());
    assertEquals(COUNTRY_MOROCCO, actual.get(1).getCountry());
    assertTrue(actual.get(1).getValidNumber());
    assertEquals(CODE_MOROCCO, actual.get(1).getCountryCode());
    assertEquals(NUMBER_MOROCCO, actual.get(1).getNumber());
  }

  @Test
  public void itShouldListOnlyMoroccoNumbers() {
    List<Customer> mockDBRegisters = List.of(buildCustomerCameroon(), buildCustomerMorocco());
    when(repository.findAll()).thenReturn(mockDBRegisters);

    List<CustomerDTO> actual = service.findAll("MOROCCO", null);

    assertNotNull(actual);
    assertEquals(1, actual.size());

    assertEquals(NAME, actual.get(0).getName());
    assertEquals(COUNTRY_MOROCCO, actual.get(0).getCountry());
    assertTrue(actual.get(0).getValidNumber());
    assertEquals(CODE_MOROCCO, actual.get(0).getCountryCode());
    assertEquals(NUMBER_MOROCCO, actual.get(0).getNumber());
  }

  @Test
  public void itShouldListOnlyInvalidNumbers() {
    Customer invalidNumberCustomer = Customer.builder()
        .id(10)
        .phone("(212) 112233445566778899")
        .name("invalid phone test")
        .build();

    List<Customer> mockDBRegisters = new ArrayList<>();
    mockDBRegisters.add(invalidNumberCustomer);
    mockDBRegisters.add(buildCustomerCameroon());
    mockDBRegisters.add(buildCustomerMorocco());

    when(repository.findAll()).thenReturn(mockDBRegisters);

    List<CustomerDTO> actual = service.findAll(null, false);

    assertNotNull(actual);
    assertEquals(1, actual.size());

    assertEquals("invalid phone test", actual.get(0).getName());
    assertFalse(actual.get(0).getValidNumber());
  }

  @Test
  public void itShouldListOnlyValidNumbers() {
    Customer invalidNumberCustomer = Customer.builder()
        .id(10)
        .phone("(212) 112233445566778899")
        .name("invalid phone test")
        .build();

    List<Customer> mockDBRegisters = new ArrayList<>();
    mockDBRegisters.add(invalidNumberCustomer);
    mockDBRegisters.add(buildCustomerCameroon());
    mockDBRegisters.add(buildCustomerMorocco());

    when(repository.findAll()).thenReturn(mockDBRegisters);

    List<CustomerDTO> actual = service.findAll(null, true);

    assertNotNull(actual);
    assertEquals(2, actual.size());

    assertEquals(NUMBER_CAMEROON, actual.get(0).getNumber());
    assertTrue(actual.get(0).getValidNumber());
    assertEquals(NUMBER_MOROCCO, actual.get(1).getNumber());
    assertTrue(actual.get(1).getValidNumber());
  }

  @Test
  public void itShouldListNoNumbers() {
    Customer invalidNumberCustomer = Customer.builder()
        .id(10)
        .phone("(212) 112233445566778899")
        .name("invalid phone test")
        .build();

    List<Customer> mockDBRegisters = new ArrayList<>();
    mockDBRegisters.add(invalidNumberCustomer);
    mockDBRegisters.add(buildCustomerCameroon());
    mockDBRegisters.add(buildCustomerMorocco());

    when(repository.findAll()).thenReturn(mockDBRegisters);

    List<CustomerDTO> actual = service.findAll("CAMEROON", false);

    assertNotNull(actual);
    assertEquals(0, actual.size());
  }

  private Customer buildCustomerCameroon() {
    return Customer.builder().id(2).name(NAME).phone(PHONE_CAMEROON).build();
  }

  private Customer buildCustomerMorocco() {
    return Customer.builder().id(1).name(NAME).phone(PHONE_MOROCCO).build();
  }

}