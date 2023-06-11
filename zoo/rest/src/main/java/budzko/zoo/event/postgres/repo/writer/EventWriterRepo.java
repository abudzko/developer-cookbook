package budzko.zoo.event.postgres.repo.writer;

import budzko.zoo.event.postgres.repo.writer.entity.WriteEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventWriterRepo extends JpaRepository<WriteEventEntity, String> {
}
