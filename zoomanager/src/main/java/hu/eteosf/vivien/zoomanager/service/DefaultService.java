package hu.eteosf.vivien.zoomanager.service;

import hu.eteosf.vivien.zoomanager.mapper.Mapper;
import hu.eteosf.vivien.zoomanager.model.AnimalDTO;
import hu.eteosf.vivien.zoomanager.model.ZooDTO;
import hu.eteosf.vivien.zoomanager.repository.AnimalEntity;
import hu.eteosf.vivien.zoomanager.repository.AnimalRepository;
import hu.eteosf.vivien.zoomanager.repository.ZooEntity;
import hu.eteosf.vivien.zoomanager.repository.ZooRepository;
import hu.eteosf.vivien.zoomanager.service.exception.EntityNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
public class DefaultService implements ZooService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultService.class);
    private final ZooRepository zooRepo;
    private final AnimalRepository animalRepo;
    private Mapper mapper = new Mapper();

    @Autowired
    public DefaultService(ZooRepository zooRepo, AnimalRepository animalRepo) {
        this.zooRepo = zooRepo;
        this.animalRepo = animalRepo;
    }

    @Override
    public void createNewZoo(ZooDTO zoo) {
        LOGGER.info("createNewZoo method is running.");
        final ZooEntity zooEntity = mapper.zooDTOtoEntity(zoo);
        zooRepo.save(zooEntity);
    }

    @Override
    public void createAnimal(Long zooId, AnimalDTO animal) {
        LOGGER.info("createAnimal method is running.");
        Optional<ZooEntity> zooEntity = zooRepo.findByOwnid(zooId);
        if (!zooEntity.isPresent()) {
            LOGGER.error("The zoo with the given id: {} does not exist.", zooId);
            throw new EntityNotExistException("The zoo does not exist");
        }
        ZooEntity zoo = zooEntity.get();
        AnimalEntity animalEntity = mapper.animalDTOToEntity(animal);
        zoo.addItem(animalEntity);
        animalRepo.save(animalEntity);
    }

    @Override
    public void deleteOneZoo(Long zooId) {
        LOGGER.info("deleteOneZoo method is running.");
        Optional<ZooEntity> opt = zooRepo.findByOwnid(zooId);
        if (!opt.isPresent()) {
            LOGGER.error("The zoo with the given id: {} does not exist.", zooId);
            throw new EntityNotExistException("The unique id does not exist");
        }
        ZooEntity zoo = opt.get();
        deleteAnimalsFromZoo(zoo, zoo.getListOfAnimals());
        zooRepo.deleteByOwnid(zooId);
    }

    public void deleteAnimalsFromZoo(ZooEntity zooEntity, Collection<AnimalEntity> animalEntities) {
        LOGGER.info("deleteAnimalsFromZoo method is running.");
        zooEntity.removeItems(animalEntities);
    }

    @Override
    public Collection<ZooDTO> getZoos() {
        LOGGER.info("getZoos method is running.");
        if (zooRepo.count() > 0) {
            return mapper.zooEntityListToDTO(zooRepo.findAll());
        }
        LOGGER.error("The zoo repository is empty.");
        throw new EntityNotExistException("There is no available zoo.");
    }

    @Override
    public AnimalDTO getOneAnimalById(Long zooId, Long animalId) {
        LOGGER.info("getOneAnimalById method is running.");
        Optional<AnimalEntity> opt = animalRepo.findByOwnid(animalId);
        if (!opt.isPresent()) {
            LOGGER.error("The animal with the given id: {} does not exist.", animalId);
            throw new EntityNotExistException("Animal does not exist with the given unique id");
        }
        return mapper.animalEntityToDTO(opt.get());
    }

    @Override
    public Collection<AnimalDTO> getAllAnimals(Long zooId) {
        LOGGER.info("getAllAnimals method is running.");
        ZooDTO zoo = getOneZoo(zooId);
        if (zoo.getListOfAnimals().isEmpty()) {
            LOGGER.error("The given zoo with {} id has no animal.", zooId);
            throw new EntityNotExistException("In the given zoo the animal repo is empty");
        }
        return zoo.getListOfAnimals();
    }

    @Override
    public ZooDTO getOneZoo(Long zooId) {
        LOGGER.info("getOneZoo method is running.");
        final Optional<ZooEntity> zooEntityOpt = zooRepo.findByOwnid(zooId);
        if (!zooEntityOpt.isPresent()) {
            LOGGER.error("The zoo with the given id: {} does not exist.", zooId);
            throw new EntityNotExistException("The zoo with the given unique id does not exist");
        }
        return mapper.zooEntityToDTO(zooEntityOpt.get());
    }

    @Override
    public ZooDTO updateZoo(Long zooId, ZooDTO zoo) {
        LOGGER.info("updateZoo method is running.");
        final Optional<ZooEntity> zooEntityOpt = zooRepo.findByOwnid(zooId);

        if (!zooEntityOpt.isPresent()) {
            LOGGER.error("The zoo with the given id: {} does not exist.", zooId);
            throw new EntityNotExistException("There is no existing zoo with the given id");
        }

        final ZooEntity updatedEntity = updateZooEntity(zoo, zooEntityOpt.get());
        return mapper.zooEntityToDTO(updatedEntity);
    }

    private ZooEntity updateZooEntity(ZooDTO from, ZooEntity to) {
        LOGGER.info("updateZooEntity method is running.");
        if (!from.getName().equals(to.getName()))
            to.setName(from.getName());

        if (!from.getAddress().equals(to.getAddress()))
            to.setAddress(from.getAddress());

        return to;
    }

    @Override
    public AnimalDTO updateAnimal(Long animalId, AnimalDTO animal) {
        LOGGER.info("updateAnimalEntity method is running.");
        final Optional<AnimalEntity> animalEntityOpt = animalRepo.findByOwnid(animalId);

        if (!animalEntityOpt.isPresent()) {
            LOGGER.error("The animal with the given id: {} does not exist.", animalId);
            throw new EntityNotExistException("There is no existing animal with the given id");
        }

        final AnimalEntity updatedEntity = updateAnimalEntity(animal, animalEntityOpt.get());
        return mapper.animalEntityToDTO(updatedEntity);
    }

    private AnimalEntity updateAnimalEntity(AnimalDTO from, AnimalEntity to) {
        LOGGER.info("updateAnimalEntity method is running.");
        if (!from.getName().equals(to.getName()))
            to.setName(from.getName());

        if (!from.getAge().equals(to.getAge()))
            to.setAge(from.getAge());

        if (!from.getColor().equals(to.getColor()))
            to.setColor(from.getColor());

        return to;
    }

    @Transactional
    public void deleteAnimal(Long zooId, Long animalId) {
        LOGGER.info("deleteAnimal method is running.");
        Optional<AnimalEntity> opt = animalRepo.findByOwnid(animalId);
        if (!opt.isPresent()) {
            LOGGER.error("The animal with the given id: {} does not exist.", animalId);
            throw new EntityNotExistException("The unique id does not exist");
        }
        animalRepo.deleteByOwnid(animalId);
    }

}