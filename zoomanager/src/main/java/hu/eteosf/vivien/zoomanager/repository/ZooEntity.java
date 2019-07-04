package hu.eteosf.vivien.zoomanager.repository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "zoo")
public class ZooEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "ownid", unique = true)
    private Long ownid;


    @OneToMany(mappedBy = "zooEntity", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<AnimalEntity> listOfAnimals = new ArrayList<>();

    public ZooEntity(Long ownid, String name, String address) {
        this.ownid = ownid;
        this.name = name;
        this.address = address;
    }

    public ZooEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public Long getOwnid() {
        return ownid;
    }

    public void setOwnid(Long ownid) {
        this.ownid = ownid;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<AnimalEntity> getListOfAnimals() {
        return Collections.unmodifiableList(listOfAnimals);
    }

    public void addItem(AnimalEntity animalEntity) {
        animalEntity.setZooEntity(this);
        listOfAnimals.add(animalEntity);
    }

    public void addItems(List<AnimalEntity> animalEntities) {
        for (final AnimalEntity animal : animalEntities) {
            animal.setZooEntity(this);
            listOfAnimals.add(animal);
        }
    }

    public void removeItems(Collection<AnimalEntity> animalEntities) {
        listOfAnimals.removeAll(animalEntities);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ZooEntity zooEntity = (ZooEntity) o;

        if (id != null ? !id.equals(zooEntity.id) : zooEntity.id != null) return false;
        if (name != null ? !name.equals(zooEntity.name) : zooEntity.name != null) return false;
        if (address != null ? !address.equals(zooEntity.address) : zooEntity.address != null) return false;
        if (ownid != null ? !ownid.equals(zooEntity.ownid) : zooEntity.ownid != null) return false;
        return listOfAnimals != null ? listOfAnimals.equals(zooEntity.listOfAnimals) : zooEntity.listOfAnimals == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (ownid != null ? ownid.hashCode() : 0);
        result = 31 * result + (listOfAnimals != null ? listOfAnimals.hashCode() : 0);
        return result;
    }
}
