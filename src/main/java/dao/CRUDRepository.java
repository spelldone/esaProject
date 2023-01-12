package dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CRUDRepository<Entity> {

    Optional<Entity> retrieveById(UUID id);

    List<Entity> retrieveAll();

    void save(Entity entity);

    void update(Entity entity);

    void delete(Entity entity);

}
