package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import xyz.durianyang.config.ApplicationContext;
import xyz.durianyang.dao.RoleDao;
import xyz.durianyang.dao.UserDao;
import xyz.durianyang.entity.Role;
import xyz.durianyang.entity.User;

/**
 * 测试多对多'
 * 需要中间表
 * @author Durian
 * @date 2019-09-24 20:00
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationContext.class)
public class ManyToManyTest
{
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserDao userDao;

    /**
     * 保存一个用户和一个角色
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testManyToManyAdd()
    {
        User user = new User();
        Role role = new Role();
        user.setUserName("Jack");
        role.setRoleName("管理员");
        //建立联系
        user.getRoles().add(role);
        role.getUsers().add(user);
        userDao.save(user);
        roleDao.save(role);
    }

    /**
     * 级联操作
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testManyToManyAdd2()
    {
        User user = new User();
        Role role1 = new Role();
        Role role2 = new Role();
        user.setUserName("Lucy");
        role1.setRoleName("普通用户");
        role2.setRoleName("高级会员");
        //建立联系
        user.getRoles().add(role1);
        user.getRoles().add(role2);
        role1.getUsers().add(user);
        userDao.save(user);
    }

    /**
     * 级联删除，慎用
     * 需要在主操作表中开启cascade = CascadeType.ALL
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testRemove()
    {
        userDao.delete(1L);
    }

    /**
     * 不使用级联删除
     * 不会删除角色表中信息，只删除用户表和中间表信息
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testRemove2()
    {
        userDao.delete(2L);
    }
}
