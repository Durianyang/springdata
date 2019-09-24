package xyz.durianyang.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import xyz.durianyang.entity.Role;

/**
 * @author Durian
 * @date 2019-09-24 20:05
 */
@Repository
public interface RoleDao extends JpaRepository<Role,Long>, JpaSpecificationExecutor<Role>
{
}
