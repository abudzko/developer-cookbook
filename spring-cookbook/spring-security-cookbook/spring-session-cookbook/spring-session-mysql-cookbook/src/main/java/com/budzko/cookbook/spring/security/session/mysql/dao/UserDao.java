package com.budzko.cookbook.spring.security.session.mysql.dao;

import com.budzko.cookbook.spring.security.session.mysql.dao.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDao implements UserDetailsService {

    private Map<String, User> users = createUsers();

    private HashMap<String, User> createUsers() {
        HashMap<String, User> result = new HashMap<>();

        User user = createUser("user", "1", List.of("USER"));
        result.put(user.getUsername(), user);
        user = createUser("admin", "1", List.of("ROLE_USER", "ROLE_ADMIN"));
        result.put(user.getUsername(), user);
        return result;
    }

    private User createUser(String name, String password, List<String> roles) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        List<GrantedAuthority> authorities = generateAuthorities(roles);
        user.setGrantedAuthorities(authorities);
        return user;
    }

    private List<GrantedAuthority> generateAuthorities(List<String> roles) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (String role : roles) {
            GrantedAuthority grantedAuthority = (GrantedAuthority) () -> {
                //not simplified: for debug
                return role;
            };
            grantedAuthorities.add(grantedAuthority);
        }
        return grantedAuthorities;
    }

    @Override

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return users.get(s);
    }
}
