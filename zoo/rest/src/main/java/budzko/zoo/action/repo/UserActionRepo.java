package budzko.zoo.action.repo;

import budzko.zoo.action.repo.entity.UserActionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActionRepo extends JpaRepository<UserActionEntity, String> {
}
