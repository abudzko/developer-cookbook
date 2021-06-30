package com.budzko.cookbook.spring.security.session.mysql.security;

import com.budzko.cookbook.spring.security.session.mysql.dao.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;

public class CustomSecurityExpressionRoot extends WebSecurityExpressionRoot {

    CustomSecurityExpressionRoot(Authentication authentication, FilterInvocation filterInvocation) {
        super(authentication, filterInvocation);
    }

    public boolean hasAccess() {
        User user = (User) authentication.getPrincipal();
        com.budzko.log.utils.LogUtils.console(getClass().getName() + "#hasAccess:" + user.getNotSerializable());
        return true;
    }
}
