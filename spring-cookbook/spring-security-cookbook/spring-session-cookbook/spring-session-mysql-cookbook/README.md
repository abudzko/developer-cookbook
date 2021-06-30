Spring session with MySQL database<p>
Spring XML based configuration<p>
Apply tables.sql to create required tables<p>
See database connection configuration in data-source.xml<p>
com.budzko.cookbook.spring.security.session.mysql.dao.model.NotSerializable - class which will be stored in database<p>
Configured custom org.springframework.security.web.context.SecurityContextRepository:
    com.budzko.cookbook.spring.security.session.mysql.security.CustomSpringSecurityContextRepository<p>
Configured custom org.springframework.security.access.expression.SecurityExpressionRoot:
    com.budzko.cookbook.spring.security.session.mysql.security.CustomSecurityExpressionRoot<p>
