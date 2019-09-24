package xyz.durianyang.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Durian
 * @date 2019-09-22 15:40
 */
@Setter
@Getter
@ToString
@Entity
@Table(name = "cst_customer")
public class Customer
{
    @Id//声明主键的配置,自动增长
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private Long custId;
    @Column(name = "cust_name")
    private String custName;//名称
    @Column(name = "cust_source")
    private String custSource;//来源
    @Column(name = "cust_level")
    private String custLevel;//级别
    @Column(name = "cust_industry")
    private String custIndustry;//行业
    @Column(name = "cust_phone")
    private String custPhone;//电话
    @Column(name = "cust_address")
    private String custAddress;//地址
    //使用注解配置一对多关系:1、声明关系 2、配置外键,配置了外键，在多的一方维护外键
//    @OneToMany(targetEntity = LinkMan.class, fetch = FetchType.LAZY) // 放弃外键维护权
//    @JoinColumn(name = "lkm_cust_id", referencedColumnName = "cust_id")
    //name值为对方属性名称Set<Customer> customers
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<LinkMan> linkManSet = new HashSet<LinkMan>();
}
