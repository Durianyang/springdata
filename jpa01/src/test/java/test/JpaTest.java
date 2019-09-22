package test;

import org.junit.Test;
import xyz.durianyang.entity.Customer;
import xyz.durianyang.util.JpaUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 测试JPA
 * @author Durian
 * @date 2019-09-22 15:56
 */
public class JpaTest
{
    /**
     * Jpa操作步骤
     * 1、加载配置文件创建工厂
     * 2、通过工厂获取实体管理器
     * 3、开启事务
     * 4、完成curd
     * 5、事务提交或者回滚
     * 6、释放资源
     */
    @Test
    public void testSave()
    {
        //创建比较浪费资源,且为线程安全
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        //开启事务
        transaction.begin();
        Customer customer = new Customer();
        customer.setCustName("李四");
        customer.setCustAddress("长沙");
        //保存对象到数据库
        entityManager.persist(customer);
        //提交事务
        transaction.commit();
        //释放资源
        entityManager.close();
        factory.close();
    }

    @Test
    public void testJpaUtils()
    {
        EntityManager entityManager = JpaUtils.createEntityManage();
        EntityTransaction transaction = entityManager.getTransaction();
        //开启事务
        transaction.begin();
        Customer customer = new Customer();
        customer.setCustName("张三");
        customer.setCustAddress("北京");
        //保存对象到数据库
        entityManager.persist(customer);
        //提交事务
        transaction.commit();
        //释放资源
        entityManager.close();
    }

    /**
     * find方法查询会立即加载
     */
    @Test
    public void testFind()
    {
        EntityManager entityManager = JpaUtils.createEntityManage();
        Customer customer = entityManager.find(Customer.class, 2L);
        System.out.println(customer);
        //释放资源
        entityManager.close();
    }

    /**
     * getReference方法会延迟加载
     */
    @Test
    public void testGetReference()
    {
        EntityManager entityManager = JpaUtils.createEntityManage();
        Customer customer = entityManager.getReference(Customer.class, 2L);
        System.out.println(customer);
        //释放资源
        entityManager.close();
    }

    /**
     * 先查询相应对象再从数据库删除该对象
     */
    @Test
    public void testRemove()
    {
        EntityManager entityManager = JpaUtils.createEntityManage();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(entityManager.find(Customer.class, 1L));
        transaction.commit();
        //释放资源
        entityManager.close();
    }

    /**
     * 更新数据库记录
     */
    @Test
    public void testUpdate()
    {
        EntityManager entityManager = JpaUtils.createEntityManage();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Customer customer = entityManager.find(Customer.class, 3L);
        customer.setCustAddress("广州");
        customer.setCustLevel("小康");
        Customer merge = entityManager.merge(customer);
        System.out.println(merge);
        transaction.commit();
        //释放资源
        entityManager.close();
    }
}
