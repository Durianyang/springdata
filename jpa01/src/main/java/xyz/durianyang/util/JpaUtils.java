package xyz.durianyang.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Durian
 * @date 2019-09-22 19:46
 */
public class JpaUtils
{
    private static EntityManagerFactory factory;

    static
    {
        factory = Persistence.createEntityManagerFactory("myJpa");
    }

    public static EntityManager createEntityManage()
    {
        return factory.createEntityManager();
    }
}
