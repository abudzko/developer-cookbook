package budzko.zoo.action.service.dto;

import lombok.Data;

@Data
public class UserActionDto {
    private String id;
    private String userId;
    private String userAction;
    private Long actionValue;
}
