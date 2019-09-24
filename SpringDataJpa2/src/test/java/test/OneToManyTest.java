package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import xyz.durianyang.config.ApplicationContext;
import xyz.durianyang.config.DataSourceConfig;
import xyz.durianyang.dao.CustomerDao;
import xyz.durianyang.dao.LinkManDao;
import xyz.durianyang.entity.Customer;
import xyz.durianyang.entity.LinkMan;

import java.util.List;

/**
 * @author Durian
 * @date 2019-09-24 18:43
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationContext.class, DataSourceConfig.class})
public class OneToManyTest
{
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private LinkManDao linkManDao;

    @Test
    @Transactional
    @Rollback(false)
    public void testAdd()
    {
        Customer customer = new Customer();
        customer.setCustName("C Company");
        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("张先生");
        //建立连个记录间的联系
//        customer.getLinkManSet().add(linkMan);
        linkMan.setCustomer(customer);//此方式会少执行一条sql语句 update linkman set lkm_cust_id = ? where cust_id = ?
        customerDao.save(customer);
        linkManDao.save(linkMan);
    }

    /**
     * 测试级联添加
     * 需要在操作主体的实体类上，配置cascade属性
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testAdd2()
    {
        Customer customer = new Customer();
        customer.setCustName("S Company");
        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("玉先生");
        customer.getLinkManSet().add(linkMan);
        linkMan.setCustomer(customer);
        customerDao.save(customer);
    }

    /**
     * 级联删除，删除1号客户时同时删除所有联系人
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testRemove()
    {
        Customer customer = customerDao.findOne(5L);
        customerDao.delete(customer);
    }

    @Test
    @Transactional(value = "transactionManager")
    @Rollback(false)
    public void testFind()
    {
        List<Customer> customers = customerDao.findAll();
        for (Customer customer : customers)
        {
            System.out.println(customer);
        }
    }

}
