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
 * @date 2019-09-23 18:47
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JpqlTest
{
    @Autowired
    private CustomerService customerService;

    @Test
    public void testFindByName()
    {
        Customer customer = customerService.findByName("李四");
        System.out.println(customer);
    }

    @Test
    public void testFindByNameAndById()
    {
        Customer customer = customerService.findByNameAndById("李四", 5L);
        System.out.println(customer);
    }

    /**
     * SpringDataJpa使用JPQL更新/删除需要事务支持
     *      手动添加事务
     *      并且默认回滚事务
     */
    @Test
    public void testUpdateNameById()
    {
        customerService.updateNameById(5L, "王二");
    }

    @Test
    public void testFindBySql()
    {
        List<Customer> customerArray = customerService.findBySql();
        for (Customer customer : customerArray)
        {
            System.out.println(customer);
        }
    }

    @Test
    public void findBySqlName()
    {
        List<Customer> customerArray = customerService.findBySqlName("%王%");
        for (Customer customer : customerArray)
        {
            System.out.println(customer);
        }
    }

    /**
     * 使用方法命名规则查询,findBy 开头，表示查询,默认使用=查询而不是用LIKE
     *  + 对象中的属性名，首字母大写
     *  + 查询方式(LIKE/isNull)
     *  + 多条件连接符
     *  含义：根据对应属性查询
     */
    @Test
    public void findByCustName()
    {
        Customer customer = customerService.findByCustName("王五");
        System.out.println(customer);
    }

    @Test
    public void findByCustNameLike()
    {
        List<Customer> customerList = customerService.findByCustNameLike("%五%");
        for (Customer customer : customerList)
        {
            System.out.println(customer);
        }
    }

    @Test
    public void findByCustNameLikeAndCustAddress()
    {
        List<Customer> customerList = customerService.findByCustNameLikeAndCustAddress("%王%", "长沙   ");
        for (Customer customer : customerList)
        {
            System.out.println(customer);
        }
    }

}
