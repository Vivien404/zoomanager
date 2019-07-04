package hu.eteosf.vivien.zoomanager.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ZooRepository extends CrudRepository<ZooEntity, Long> {
    Optional<ZooEntity> findByOwnid(Long id);

    void deleteByOwnid(Long id);
}
