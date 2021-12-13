package com.heliorodri.jumia.exercise.domain.customer.repository;

import com.heliorodri.jumia.exercise.domain.customer.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

  List<Customer> findAll();

}
