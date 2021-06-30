package com.budzko.cookbook.spring.security.session.mysql.security;

import com.budzko.cookbook.spring.security.session.mysql.dao.model.NotSerializable;
import com.budzko.cookbook.spring.security.session.mysql.dao.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

public class CustomSpringSecurityContextRepository extends HttpSessionSecurityContextRepository {

    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        SecurityContext securityContext = super.loadContext(requestResponseHolder);
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal != null) {
                if (principal instanceof User) {
                    User user = (User) principal;
                    user.setNotSerializable(new NotSerializable());
                }
                com.budzko.log.utils.LogUtils.console(principal.getClass().toString());
            }
        }
        return securityContext;
    }
}
