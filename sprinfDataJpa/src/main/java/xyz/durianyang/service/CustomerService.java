package xyz.durianyang.service;

import xyz.durianyang.entity.Customer;

import java.util.List;

/**
 * @author Durian
 * @date 2019-09-23 16:04
 */
public interface CustomerService
{
    Customer findOne(Long id);

    void save(Customer customer);

    void delete(Long id);

    void delete(Customer customer);

    List<Customer> findAll();
}
