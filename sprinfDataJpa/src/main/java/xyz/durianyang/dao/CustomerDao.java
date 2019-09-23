package xyz.durianyang.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import xyz.durianyang.entity.Customer;

/**
 * JpaRepository<T, ID>，T是要操作的实体类，ID时主键数据类型,封装了基本CURD操作
 * JpaSpecificationExecutor<T>，T是要操作的实体类,封装了复杂查询(分页查询)
 * @author Durian
 * @date 2019-09-23 16:03
 */
public interface CustomerDao extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer>
{
    
}
