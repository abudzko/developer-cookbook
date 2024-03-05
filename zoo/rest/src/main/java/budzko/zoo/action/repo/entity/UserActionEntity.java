package budzko.zoo.action.repo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity(name = "user_action")
@Table(schema = "zoo")
@Data
public class UserActionEntity {
    @Id
    private String id;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "user_action")
    private String userAction;
    @Column(name = "action_value")
    private Long actionValue;
}
