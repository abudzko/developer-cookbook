package com.budzko.app.hibernate.service;

import com.budzko.app.hibernate.config.HibernateUtil;
import com.budzko.app.jpa.repo.entity.UserEntity;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Service
public class UserService {

    @PostConstruct
    void doWork(){
//        createUser();
    }
    public void createUser() {
        UserEntity user = new UserEntity();
        user.setId(UUID.randomUUID().toString());
        user.setName("Ivan");

        //Get Session
        Session session = HibernateUtil.getSessionAnnotationFactory().getCurrentSession();
        //start transaction
        session.beginTransaction();
        //Save the Model object
        session.save(user);
        //Commit transaction
        session.getTransaction().commit();
        System.out.println("UserEntity ID=" + user.getId());

        //terminate session factory, otherwise program won't end
        HibernateUtil.getSessionAnnotationFactory().close();
    }
}
