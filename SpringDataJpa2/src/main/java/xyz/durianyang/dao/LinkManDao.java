package xyz.durianyang.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import xyz.durianyang.entity.LinkMan;

/**
 * @author Durian
 * @date 2019-09-24 18:24
 */
@Repository
public interface LinkManDao extends JpaRepository<LinkMan, Long>, JpaSpecificationExecutor<LinkMan>
{

}
