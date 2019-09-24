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


    public long getCount()
    {
        return customerDao.count();
    }

    public boolean exists(Long id)
    {
        return customerDao.exists(id);
    }

    public Customer getOne(Long id)
    {
        return customerDao.getOne(id);
    }

    public Customer findByName(String name)
    {
        return customerDao.findByName(name);
    }

    public Customer findByNameAndById(String name, Long id)
    {
        return customerDao.findByNameAndById(name, id);
    }

    public void updateNameById(Long id, String name)
    {
        customerDao.updateNameById(id, name);
    }

    public List<Customer> findBySql()
    {
        return customerDao.findBySql();
    }

    public List<Customer> findBySqlName(String name)
    {
        return customerDao.findBySqlName(name);
    }

    public Customer findByCustName(String name)
    {
        return customerDao.findByCustName(name);
    }

    public List<Customer> findByCustNameLike(String name)
    {
        return customerDao.findByCustNameLike(name);
    }

    public List<Customer> findByCustNameLikeAndCustAddress(String name, String address)
    {
        return customerDao.findByCustNameLikeAndCustAddress(name, address);
    }
}