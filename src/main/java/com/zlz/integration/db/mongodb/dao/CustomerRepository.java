package com.zlz.integration.db.mongodb.dao;

import java.util.List;
import java.util.Optional;

import com.zlz.integration.db.mongodb.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {

  public List<Customer> findByFirstName(String firstName);

  public List<Customer> findByLastName(String lastName);

  public Optional<Customer> findById(String s);

  @Override
  public <S extends Customer> S insert(S s);
}