package com.budzko.app.jpa.service;

import com.budzko.app.jpa.repo.entity.CarEntity;
import com.budzko.app.jpa.repo.entity.PassportEntity;
import com.budzko.app.jpa.repo.entity.PasswordEntity;
import com.budzko.app.jpa.repo.entity.UserCommentEntity;
import com.budzko.app.jpa.repo.entity.UserEntity;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class EntityUtils {

    public static final String CAR_COUNT = "carCount";
    public static final String PASSWORD_COUNT = "passwordCount";

    public static Set<UserEntity> createUserEntities(int count, Map<String, Integer> parameters) {
        HashSet<UserEntity> userEntities = new HashSet<>();
        for (int i = 0; i < count; i++) {
            UserEntity userEntity = createUserEntity();
            userEntities.add(userEntity);
            if (parameters.containsKey(CAR_COUNT)) {
                userEntity.setCars(createCarEntities(parameters.get(CAR_COUNT)));
            }
            if (parameters.containsKey(PASSWORD_COUNT)) {
                userEntity.setPasswords(createPasswordEntities(parameters.get(PASSWORD_COUNT)));
            }
            userEntity.setPassportEntity(createPassportEntity());
        }
        return userEntities;
    }

    public static Set<UserCommentEntity> createUserCommentEntities(int count, UserEntity userEntity) {
        Set<UserCommentEntity> userCommentEntities = new HashSet<>();
        for (int i = 0; i < count; i++) {
            UserCommentEntity userCommentEntity = new UserCommentEntity();
            userCommentEntity.setId(generateId());
            userCommentEntity.setUserEntity(userEntity);
            userCommentEntities.add(userCommentEntity);
        }
        return userCommentEntities;
    }

    public static Set<CarEntity> createCarEntities(int count) {
        Set<CarEntity> carEntities = new HashSet<>();
        for (int i = 0; i < count; i++) {
            carEntities.add(createCarEntity());
        }
        return carEntities;
    }

    public static Set<PasswordEntity> createPasswordEntities(int count) {
        Set<PasswordEntity> passwordEntities = new HashSet<>();
        for (int i = 0; i < count; i++) {
            passwordEntities.add(createPasswordEntity());
        }
        return passwordEntities;
    }

    public static UserEntity createUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(generateId());
        userEntity.setName(generateId());
        return userEntity;
    }

    public static CarEntity createCarEntity() {
        CarEntity carEntity = new CarEntity();
        carEntity.setCarNumber(generateId());
        return carEntity;
    }

    public static PasswordEntity createPasswordEntity() {
        PasswordEntity passwordEntity = new PasswordEntity();
        passwordEntity.setId(generateId());
        passwordEntity.setPassword(generateId());
        return passwordEntity;
    }

    public static PassportEntity createPassportEntity() {
        PassportEntity passportEntity = new PassportEntity();
        passportEntity.setId(generateId());
        passportEntity.setNumber(generateId());
        return passportEntity;
    }

    public static String generateId() {
        return UUID.randomUUID().toString();
    }
}
