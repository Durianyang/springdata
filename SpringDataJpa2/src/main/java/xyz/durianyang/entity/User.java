package xyz.durianyang.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Durian
 * @date 2019-09-24 20:01
 */
@Setter
@Getter
@ToString
@Entity
@Table(name = "user")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "user_name")
    private String userName;
    @ManyToMany(targetEntity = Role.class, fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_role",
            //joinColumn：当前对象主键在中间表的外键
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            //inverseJoinColumns：对方对象主键在中间表的外键
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "role_id")}
            )
    private Set<Role> roles = new HashSet<Role>();
}
