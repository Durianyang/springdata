package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xyz.durianyang.dao.CustomerDao;
import xyz.durianyang.entity.Customer;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * 使用JpaSpecificationExecutor动态查询
 *
 * @author Durian
 * @date 2019-09-24 15:29
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpecTest
{
    /**
     * 为简化测试，不再使用业务类
     */
    @Autowired
    private CustomerDao customerDao;


    /**
     * 单条件查询
     */
    @Test
    public void testSpec()
    {
        /*
          匿名内部类
          自定义查询条件:
          1、实现Specification接口（提供泛型，查询的对象类型）
          2、实现toPredicate方法（构造查询条件）
          3、需要借助方法参数中的两个参数：
               root：获取需要查询的对象属性
               CriteriaQuery：构造查询条件，内部封装了很多查询条件（模糊查询，精准匹配）
         */
        Specification<Customer> spec = new Specification<Customer>()
        {
            /**
             * 定义查询方式
             * @param root 比较的属性
             * @param query null
             * @param cb 查询方式
             * @return 结果
             */
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb)
            {
                /*
                 * select * from cust_customer where cust_name = '';
                 */
                //获取比较的属性
                Path<Object> custName = root.get("custName");
                //构造查询条件
                return cb.equal(custName, "张三");
            }
        };
        Customer customer = customerDao.findOne(spec);
        System.out.println(customer);
    }

    @Test
    public void testSpec2()
    {
        Specification<Customer> spec = new Specification<Customer>()
        {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb)
            {
                Path<Object> custName = root.get("custName");
                Path<Object> custId = root.get("custId");
                Predicate pr1 = cb.equal(custName, "张三");
                Predicate pr2 = cb.equal(custId, 3L);
                /*
                    分情况联合多个查询条件
                    and or any abs
                 */
                return cb.and(pr1, pr2);
            }
        };
        Customer customer = customerDao.findOne(spec);
        System.out.println(customer);
    }

    /**
     * 模糊匹配出所有记录
     */
    @Test
    public void testFindAll()
    {
        Specification<Customer> spec = new Specification<Customer>()
        {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb)
            {
                Path<Object> custName = root.get("custName");
                //模糊匹配需要指定参数类型path.as(x.class)
                return cb.like(custName.as(String.class),"%王%");
            }
        };
        List<Customer> customerList = customerDao.findAll(spec);
        for (Customer customer : customerList)
        {
            System.out.println(customer);
        }
    }

    /**
     * 测试排序
     */
    @Test
    public void testOrder()
    {
        Specification<Customer> spec = new Specification<Customer>()
        {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb)
            {
                Path<Object> custName = root.get("custName");
                //模糊匹配需要指定参数类型path.as(x.class)
                return cb.like(custName.as(String.class),"%%");
            }
        };
        //创建排序对象
        Sort sort = new Sort(Sort.Direction.DESC, "custId");

        //同时使用Sort和Spec
        List<Customer> customerList = customerDao.findAll(spec, sort);
        for (Customer customer : customerList)
        {
            System.out.println(customer);
        }
        //只使用Sort
        List<Customer> customers = customerDao.findAll(sort);
        for (Customer customer : customers)
        {
            System.out.println(customer);
        }
    }

    /**
     * 测试不带Specification条件的分页查询
     */
    @Test
    public void testPageable()
    {
        //创建Pageable接口的实现类
        /**
         * 第一个参数：当前页：从0开始
         * 第二个参数：每页记录条数
         */
        Pageable pageable = new PageRequest(0,2);
        Page<Customer> Pages = customerDao.findAll(null, pageable);
        List<Customer> customers = Pages.getContent();//得到数据集合
        for (Customer customer : customers)
        {
            System.out.println(customer);
        }
        System.out.println("getTotalPages:" + Pages.getTotalPages());//总页数
        System.out.println("getNumber:" + Pages.getNumber());//当前页码
        System.out.println("getSize:" + Pages.getSize());//每页最多记录数
        System.out.println("getTotalElements:" + Pages.getTotalElements());//总记录数
        System.out.println("getSort:" + Pages.getSort());//
        System.out.println("getNumberOfElements:" + Pages.getNumberOfElements());//当前页记录数

    }
}
