package xyz.durianyang.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.annotation.Rollback;
import xyz.durianyang.entity.Customer;

import java.util.List;

/**
 * JpaRepository<T, ID>，T是要操作的实体类，ID时主键数据类型,封装了基本CURD操作
 * JpaSpecificationExecutor<T>，T是要操作的实体类,封装了复杂查询(分页查询)
 * HQL没有插入方法
 * 注解query中的属性nativeQuery可以设置sql还是HQL
 *
 * @author Durian
 * @date 2019-09-23 16:03
 */
public interface CustomerDao extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer>
{
    /**
     * 使用@query配置jpql语句
     * 根据名称查询顾客
     *
     * @param name 名称
     * @return 结果
     */
    @Query(value = "from Customer where custName=?1")
    Customer findByName(String name);

    @Query(value = "from Customer where custName=?1 and custId=?2")
    Customer findByNameAndById(String name, Long id);

    @Query(value = "update Customer set custName=?2 where custId=?1")
    @Modifying
    @Rollback(false)
    void updateNameById(Long id, String name);

    /**
     * =============================使用sql操作
     */
    @Query(value = "select * from cst_customer", nativeQuery = true)
    List<Customer> findBySql();

    @Query(value = "select * from cst_customer where cust_name like ?1", nativeQuery = true)
    List<Customer> findBySqlName(String name);

    /**
     * =============================使用方法命名规则查询
     */
    Customer findByCustName(String name);

    List<Customer> findByCustNameLike(String name);

    //方法参数跟方法名必须要对应
    List<Customer> findByCustNameLikeAndCustAddress(String name, String address);
}
