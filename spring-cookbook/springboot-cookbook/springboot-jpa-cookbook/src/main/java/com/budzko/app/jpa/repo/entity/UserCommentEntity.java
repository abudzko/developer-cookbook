package com.budzko.app.jpa.repo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity(name = "user_comment")
public class UserCommentEntity {
    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
}
