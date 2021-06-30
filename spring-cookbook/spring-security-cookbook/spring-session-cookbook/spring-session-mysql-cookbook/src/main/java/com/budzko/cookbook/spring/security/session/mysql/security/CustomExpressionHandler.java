package com.budzko.cookbook.spring.security.session.mysql.security;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

public class CustomExpressionHandler extends DefaultWebSecurityExpressionHandler {

    @Override
    protected SecurityExpressionRoot createSecurityExpressionRoot(Authentication authentication, FilterInvocation fi) {
        CustomSecurityExpressionRoot root = new CustomSecurityExpressionRoot(authentication, fi);
        root.setPermissionEvaluator(getPermissionEvaluator());
        return root;
    }
}
