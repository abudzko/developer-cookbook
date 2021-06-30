package com.budzko.cookbook.spring.security.session.mysql.dao.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class User implements UserDetails {
    private Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    private String password;
    private String name;

    /**
     * {@code transient} to will not be serialized and stored in MySQL database.
     * Other fields will be serialized and stored in MySQL database.
     * {@code notSerializable} can contain some data from third-party packages
     * which is not implement {@link java.io.Serializable} interface and we have not chance to change it.
     * Instead storing in database {@code notSerializable} should be calculated/created after user pass authentication
     * and his session will be loaded from db but before it will be used by application.
     */
    private transient NotSerializable notSerializable;


    public void setGrantedAuthorities(Collection<GrantedAuthority> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public NotSerializable getNotSerializable() {
        return notSerializable;
    }

    public void setNotSerializable(NotSerializable notSerializable) {
        this.notSerializable = notSerializable;
    }
}
