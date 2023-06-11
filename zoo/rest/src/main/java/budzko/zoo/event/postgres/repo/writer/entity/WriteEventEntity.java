package budzko.zoo.event.postgres.repo.writer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity(name = "event")
@Table(schema = "zoo")
public class WriteEventEntity {
    @Id
    private String id;
    @Column(name = "name")
    private String name;

    @Column(name = "timestamp")
    private Long timestamp;
}
