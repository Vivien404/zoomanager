package hu.eteosf.vivien.zoomanager.service;

import hu.eteosf.vivien.zoomanager.model.AnimalDTO;
import hu.eteosf.vivien.zoomanager.model.ZooDTO;

import java.util.Collection;

public interface ZooService {

    void createNewZoo(final ZooDTO zoo);

    void createAnimal(final Long zooId, final AnimalDTO animal);

    void deleteOneZoo(final Long zooId);

    Collection<ZooDTO> getZoos();

    AnimalDTO getOneAnimalById(final Long zooId, final Long animalId);

    Collection<AnimalDTO> getAllAnimals(final Long zooId);

    ZooDTO getOneZoo(final Long zooId);

    ZooDTO updateZoo(final Long zooId, final ZooDTO zoo);

    AnimalDTO updateAnimal(final Long animalId, final AnimalDTO animal);

    void deleteAnimal(final Long zooId, final Long animalId);

}