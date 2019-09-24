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

    long getCount();

    boolean exists(Long id);

    Customer getOne(Long id);

    Customer findByName(String name);

    Customer findByNameAndById(String name, Long id);

    void updateNameById(Long id, String name);

    List<Customer> findBySql();

    List<Customer> findBySqlName(String name);

    Customer findByCustName(String name);

    List<Customer> findByCustNameLike(String name);

    List<Customer> findByCustNameLikeAndCustAddress(String name, String address);
}
