package xyz.durianyang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.durianyang.dao.CustomerDao;
import xyz.durianyang.entity.Customer;
import xyz.durianyang.service.CustomerService;

import java.util.List;

/**
 * @author Durian
 * @date 2019-09-23 16:26
 */
@Service
public class CustomerServiceImpl implements CustomerService
{
    private final CustomerDao customerDao;

    @Autowired
    public CustomerServiceImpl(CustomerDao customerDao)
    {
        this.customerDao = customerDao;
    }

    public Customer findOne(Long id)
    {
        return customerDao.findOne(id);
    }

    public void save(Customer customer)
    {
        customerDao.save(customer);
    }

    public void delete(Long id)
    {
        customerDao.delete(id);
    }

    public void delete(Customer customer)
    {
        customerDao.delete(customer);
    }

    public List<Customer> findAll()
    {
        return customerDao.findAll();
    }
}
