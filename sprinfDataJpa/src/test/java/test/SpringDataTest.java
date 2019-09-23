package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xyz.durianyang.entity.Customer;
import xyz.durianyang.service.CustomerService;

import java.util.List;

/**
 * @author Durian
 * @date 2019-09-23 16:12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpringDataTest
{
    @Autowired
    private CustomerService customerService;

    @Test
    public void testQuery()
    {
        Customer customer = customerService.findOne(3L);
        System.err.println(customer);
    }

    /**
     * save方法有主键时则为更新操作，没有主键时则为保存操作
     * 但是save更新时会覆盖所有数据项，若有一项未更新，则会变成null
     */
    @Test
    public void testSave()
    {
        Customer customer = new Customer();
        customer.setCustName("翠花");
        customer.setCustLevel("上海");
        customer.setCustPhone("12345");
        customerService.save(customer);
    }

    /**
     * 实际操作应为先查询在更新
     */
    @Test
    public void testUpdate()
    {
        Customer customer = customerService.findOne(6L);
        customer.setCustName("王五");
        customerService.save(customer);
    }

    @Test
    public void testDelete()
    {
        customerService.delete(2L);
        Customer customer = customerService.findOne(4L);
        customerService.delete(customer);
    }

    @Test
    public void testFindAll()
    {
        List<Customer> customerList = customerService.findAll();
        for (Customer customer : customerList)
        {
            System.err.println(customer);
        }
    }
}
