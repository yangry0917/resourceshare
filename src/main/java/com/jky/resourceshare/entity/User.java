package com.jky.resourceshare.entity;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Collection;

/**
 * 使用springSecurity用户类必须实现UserDetails
 */
@Data
@Entity
@Table(name = "tbl_user_info")
public class User implements UserDetails {
    @Column(name="id", length=50)
    private String id;

    @Column(name="user_name", length=20)
    private String userName;

    @Column(name="sex", length=1)
    private String sex;

    @Column(name="phone", length=20)
    private String phone;

    @Column(name="card_id", length=25)
    private String cardId;

    @Column(name="login_name", length=20)
    private String loginName;

    @Column(name="pwd", length=20)
    private String password;

    @Column(name="enable", length=1)
    private Boolean enable;

    @Column(name="unit_id", length=1)
    private String unitId;

    /**
     * 返回用户角色
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}
