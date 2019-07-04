package hu.eteosf.vivien.zoomanager.mapper;

import hu.eteosf.vivien.zoomanager.model.AnimalDTO;
import hu.eteosf.vivien.zoomanager.model.ZooDTO;
import hu.eteosf.vivien.zoomanager.repository.AnimalEntity;
import hu.eteosf.vivien.zoomanager.repository.ZooEntity;
import io.swagger.model.Animal;
import io.swagger.model.Zoo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Mapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(Mapper.class);

    public Mapper() {
    }

    // MODEL <-> DTO

    // model -> dto
    public ZooDTO zooToDTO(Zoo zoo) {
        LOGGER.debug("Mapping zoo model to zoo DTO.");
        ZooDTO zooDTO = new ZooDTO(zoo.getId(), zoo.getName(), zoo.getAddress());
        if (zoo.getAnimal() != null) {
            zooDTO.setListOfAnimals(animalListToDTO(zoo.getAnimal()));
        }
        return zooDTO;
    }

    public List<ZooDTO> zooListToDTO(Collection<Zoo> listOfZoo) {
        List<ZooDTO> zooListDTO = new ArrayList<>();
        for (final Zoo zoo : listOfZoo) {
            zooListDTO.add(new ZooDTO(zoo.getId(), zoo.getName(), zoo.getAddress()));
        }
        return zooListDTO;
    }

    public List<AnimalDTO> animalListToDTO(Collection<Animal> listOfAnimal) {
        LOGGER.debug("Mapping list of animal model to list of animal DTO.");
        List<AnimalDTO> animalListDTO = new ArrayList<>();
        for (final Animal animal : listOfAnimal) {
            animalListDTO.add(new AnimalDTO(animal.getId(), animal.getName(), Long.valueOf(animal.getAge()), animal.getColor()));
        }
        return animalListDTO;
    }

    public AnimalDTO animalToDTO(Animal animal) {
        LOGGER.debug("Mapping animal model to animal DTO.");
        return new AnimalDTO(animal.getId(), animal.getName(), animal.getAge(), animal.getColor());
    }

    // dto -> model
    public Zoo zooDTOToZoo(ZooDTO zooDTO) {
        LOGGER.debug("Mapping zoo DTO to zoo model.");
        Zoo zoo = new Zoo();
        zoo.setId(zooDTO.getId());
        zoo.setAddress(zooDTO.getAddress());
        zoo.setName(zooDTO.getName());
        if (zooDTO.getListOfAnimals() != null) {
            zoo.setAnimal(animalDTOListToAnimal(zooDTO.getListOfAnimals()));
        }
        return zoo;
    }

    public List<Zoo> zooDTOListToZoo(Collection<ZooDTO> listOfZooDTO) {
        LOGGER.debug("Mapping list of zoo DTO to zoo list.");
        List<Zoo> zooList = new ArrayList<>();
        for (final ZooDTO zooDTO : listOfZooDTO) {
            Zoo zoo = new Zoo();
            zoo.setId(zooDTO.getId());
            zoo.setAddress(zooDTO.getAddress());
            zoo.setName(zooDTO.getName());
            if (zooDTO.getListOfAnimals() != null) {
                zoo.setAnimal(animalDTOListToAnimal(zooDTO.getListOfAnimals()));
            }
            zooList.add(zoo);
        }
        return zooList;
    }

    public Animal animalDTOToAnimal(AnimalDTO animalDTO) {
        LOGGER.debug("Mapping animal DTO to animal model.");
        Animal animal = new Animal();
        animal.setId(animalDTO.getId());
        animal.setName(animalDTO.getName());
        animal.setColor(animalDTO.getColor());
        animal.setAge(animalDTO.getAge());
        return animal;
    }

    public List<Animal> animalDTOListToAnimal(Collection<AnimalDTO> animalDTOList) {
        LOGGER.debug("Mapping list of animal DTO to list of animal model.");
        List<Animal> animalList = new ArrayList<>();
        for (final AnimalDTO animal : animalDTOList) {
            Animal a = new Animal();
            a.setId(animal.getId());
            a.setAge(animal.getAge());
            a.setColor(animal.getColor());
            a.setName(animal.getName());
            animalList.add(a);
        }
        return animalList;
    }


    // ENTITY <-> DTO

    // entity -> dto
    public ZooDTO zooEntityToDTO(ZooEntity zooEntity) {
        LOGGER.debug("Mapping zoo entity to zoo DTO.");
        List<AnimalDTO> animalList = animalEntityListToDTO(zooEntity.getListOfAnimals());
        ZooDTO zooDTO = new ZooDTO(zooEntity.getOwnid(), zooEntity.getName(), zooEntity.getAddress());
        zooDTO.setListOfAnimals(animalList);
        return zooDTO;
    }

    public List<ZooDTO> zooEntityListToDTO(Iterable<ZooEntity> zooEntities) {
        LOGGER.debug("Mapping list of zoo entity to list of zoo DTO.");
        List<ZooDTO> zooListDTO = new ArrayList<>();
        for (final ZooEntity zoo : zooEntities) {
            ZooDTO zooDTO = new ZooDTO(zoo.getOwnid(), zoo.getName(), zoo.getAddress());
            if (zoo.getListOfAnimals() != null) {
                zooDTO.setListOfAnimals(animalEntityListToDTO(zoo.getListOfAnimals()));
            }
            zooListDTO.add(zooDTO);
        }
        return zooListDTO;
    }

    public AnimalDTO animalEntityToDTO(AnimalEntity animal) {
        LOGGER.debug("Mapping animal entity to animal DTO.");
        return new AnimalDTO(animal.getOwnid(), animal.getName(), animal.getAge(), animal.getColor());
    }

    public List<AnimalDTO> animalEntityListToDTO(List<AnimalEntity> animalEntities) {
        LOGGER.debug("Mapping list of animal entity to list of animal DTO.");
        List<AnimalDTO> animalListDTO = new ArrayList<>();
        for (final AnimalEntity animal : animalEntities) {
            animalListDTO.add(new AnimalDTO(animal.getOwnid(), animal.getName(), Long.valueOf(animal.getAge()),
                    animal.getColor()));
        }
        return animalListDTO;
    }


    // dto -> entity
    public ZooEntity zooDTOtoEntity(ZooDTO zooDTO) {
        LOGGER.debug("Mapping zoo DTO to zoo entity.");
        ZooEntity zooEntity = new ZooEntity(zooDTO.getId(), zooDTO.getName(),
                zooDTO.getAddress());
        if (zooDTO.getListOfAnimals() != null) {
            zooEntity.addItems(animalDTOListToEntity(zooDTO.getListOfAnimals()));
        }
        return zooEntity;
    }

    public List<ZooEntity> zooDTOListToEntity(Collection<ZooDTO> zooDTO) {
        List<ZooEntity> zooEntityList = new ArrayList<>();
        for (final ZooDTO zoo : zooDTO) {
            zooEntityList.add(new ZooEntity(zoo.getId(), zoo.getName(), zoo.getAddress()));
        }
        return zooEntityList;
    }

    public AnimalEntity animalDTOToEntity(AnimalDTO animalDTO) {
        LOGGER.debug("Mapping animal DTO to animal entity.");
        return new AnimalEntity(animalDTO.getId(), animalDTO.getName(), animalDTO.getAge(), animalDTO.getColor());
    }

    public List<AnimalEntity> animalDTOListToEntity(Collection<AnimalDTO> listOfAnimalDTO) {
        LOGGER.debug("Mapping list of animal DTO to list of animal entity.");
        List<AnimalEntity> animalListEntity = new ArrayList<>();
        for (final AnimalDTO animal : listOfAnimalDTO) {
            animalListEntity.add(new AnimalEntity(animal.getId(), animal.getName(), Long.valueOf(animal.getAge()),
                    animal.getColor()));
        }
        return animalListEntity;
    }
}
