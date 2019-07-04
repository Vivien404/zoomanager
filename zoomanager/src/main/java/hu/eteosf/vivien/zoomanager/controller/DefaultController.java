package hu.eteosf.vivien.zoomanager.controller;


import hu.eteosf.vivien.zoomanager.mapper.Mapper;
import hu.eteosf.vivien.zoomanager.service.DefaultService;
import io.swagger.api.ZooApi;
import io.swagger.model.Animal;
import io.swagger.model.Zoo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DefaultController implements ZooApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultController.class);
    final Mapper mapper = new Mapper();
    private final DefaultService zooService;

    @Autowired
    public DefaultController(final DefaultService zooService) {
        this.zooService = zooService;
    }

    @Override
    @Transactional
    public ResponseEntity<Void> addAnimal(@Valid Animal body, Integer zooId) {
        LOGGER.info("createAnimal method is called.");
        zooService.createAnimal(Long.valueOf(zooId), mapper.animalToDTO(body));
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> addZoo(@Valid Zoo body) {
        LOGGER.info("createNewZoo method is called.");
        zooService.createNewZoo(mapper.zooToDTO(body));
        return ResponseEntity.ok().build();
    }

    @Override
    @Transactional
    public ResponseEntity<Void> deleteZoo(Integer zooId) {
        LOGGER.info("deleteOneZoo method is called.");
        zooService.deleteOneZoo(Long.valueOf(zooId));
        return ResponseEntity.ok().build();
    }

    @Override
    @Transactional
    public ResponseEntity<List<Zoo>> getAllZoo() {
        LOGGER.info("getZoos method is called.");
        return ResponseEntity.ok(mapper.zooDTOListToZoo(zooService.getZoos()));
    }

    @Override
    @Transactional
    public ResponseEntity<Animal> getAnimalById(Integer zooId, Integer animalId) {
        LOGGER.info("getOneAnimalById method is called.");
        return ResponseEntity.ok(mapper.animalDTOToAnimal(zooService.getOneAnimalById(Long.valueOf(zooId), Long.valueOf(animalId))));

    }

    @Override
    @Transactional
    public ResponseEntity<List<Animal>> getAnimals(Integer zooId) {
        LOGGER.info("getAllAnimals method is called.");
        return ResponseEntity.ok(mapper.animalDTOListToAnimal(zooService.getAllAnimals(Long.valueOf(zooId))));
    }

    @Override
    @Transactional
    public ResponseEntity<Zoo> getZooById(Integer zooId) {
        LOGGER.info("getOneZoo method is called.");
        return ResponseEntity.ok(mapper.zooDTOToZoo(zooService.getOneZoo(Long.valueOf(zooId))));
    }

    @Override
    @Transactional
    public ResponseEntity<Zoo> updateZooWithForm(@Valid Zoo body, Integer zooId) {
        LOGGER.info("updateZoo method is called.");
        return ResponseEntity.ok(mapper.zooDTOToZoo(zooService.updateZoo(Long.valueOf(zooId), mapper.zooToDTO(body))));
    }

    @Override
    @Transactional
    public ResponseEntity<Animal> updateAnimalWithForm(@Valid Animal body, Integer animalId) {
        LOGGER.info("updateAnimal method is called.");
       return ResponseEntity.ok(mapper.animalDTOToAnimal(zooService.updateAnimal(Long.valueOf(animalId), mapper.animalToDTO(body))));
    }

    @Override
    @Transactional
    public ResponseEntity<Void> deleteAnimal(Integer zooId, Integer animalId) {
        LOGGER.info("deleteAnimal method is called.");
        zooService.deleteAnimal(Long.valueOf(zooId), Long.valueOf(animalId));
        return ResponseEntity.ok().build();
    }
}