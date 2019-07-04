package hu.eteosf.vivien.zoomanager.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AnimalRepository extends CrudRepository<AnimalEntity, Long> {
    Optional<AnimalEntity> findByOwnid(Long id);

    void deleteByOwnid(Long id);
}
