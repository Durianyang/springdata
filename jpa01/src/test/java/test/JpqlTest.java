package test;

import org.junit.Test;
import xyz.durianyang.util.JpaUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;


/**]
 * Java Persistence Query Language
 * 不支持select *
 * 但支持其他select
 * @author Durian
 * @date 2019-09-22 20:30
 */
public class JpqlTest
{
    @Test
    public void findAll()
    {
        EntityManager entityManager = JpaUtils.createEntityManage();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        String jpql = "from xyz.durianyang.entity.Customer";
        Query query = entityManager.createQuery(jpql);
        List resultList = query.getResultList();
        for (Object o : resultList)
        {
            System.out.println(o);
        }
        transaction.commit();
        entityManager.close();
    }

    /**
     * 按ID倒叙查找
     */
    @Test
    public void reversed()
    {
        EntityManager entityManager = JpaUtils.createEntityManage();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        String jpql = "from xyz.durianyang.entity.Customer order by id desc";
        Query query = entityManager.createQuery(jpql);
        List resultList = query.getResultList();
        for (Object o : resultList)
        {
            System.out.println(o);
        }
        transaction.commit();
        entityManager.close();
    }

    /**
     * 统计查询
     */
    @Test
    public void testCount()
    {
        EntityManager entityManager = JpaUtils.createEntityManage();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        String jpql = "select count(id) from xyz.durianyang.entity.Customer";
        Query query = entityManager.createQuery(jpql);
        Object result = query.getSingleResult();
        System.out.println(result);
        transaction.commit();
        entityManager.close();
    }

    /**
     * 分页查询
     */
    @Test
    public void testPage()
    {
        EntityManager entityManager = JpaUtils.createEntityManage();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        String jpql = "from xyz.durianyang.entity.Customer";
        Query query = entityManager.createQuery(jpql);
        //起始
        query.setFirstResult(0);
        //终止
        query.setMaxResults(3);
        List resultList = query.getResultList();
        for (Object o : resultList)
        {
            System.err.println(o);
        }
        transaction.commit();
        entityManager.close();
    }

    @Test
    public void testFindCondition()
    {
        EntityManager entityManager = JpaUtils.createEntityManage();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        String jpql = "from xyz.durianyang.entity.Customer where custName like ?";
        Query query = entityManager.createQuery(jpql);
        int parameter = 1;
        query.setParameter(parameter,"%四%");
        List resultList = query.getResultList();
        for (Object o : resultList)
        {
            System.out.println(o);
        }
        transaction.commit();
        entityManager.close();
    }
}
