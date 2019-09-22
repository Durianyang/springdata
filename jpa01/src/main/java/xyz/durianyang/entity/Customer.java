package xyz.durianyang.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Durian
 * @date 2019-09-22 15:40
 */
@Data
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
}
