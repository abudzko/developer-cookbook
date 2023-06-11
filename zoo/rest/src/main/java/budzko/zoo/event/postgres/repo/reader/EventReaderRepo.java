package budzko.zoo.event.postgres.repo.reader;

import budzko.zoo.event.postgres.repo.reader.entity.ReadEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventReaderRepo extends JpaRepository<ReadEventEntity, String> {
}
