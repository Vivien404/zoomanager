package hu.eteosf.vivien.zoomanager.model;

import java.util.List;

public class ZooDTO {
    private Long id;
    private String name;
    private String address;
    private List<AnimalDTO> listOfAnimals;

    public ZooDTO(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public ZooDTO() {
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

    public void setAddress(String address) {
        this.address = address;
    }

    public List<AnimalDTO> getListOfAnimals() {
        return listOfAnimals;
    }

    public void setListOfAnimals(List<AnimalDTO> listOfAnimals) {
        this.listOfAnimals = listOfAnimals;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ZooDTO zooDTO = (ZooDTO) o;

        if (id != null ? !id.equals(zooDTO.id) : zooDTO.id != null) return false;
        if (name != null ? !name.equals(zooDTO.name) : zooDTO.name != null) return false;
        if (address != null ? !address.equals(zooDTO.address) : zooDTO.address != null) return false;
        return listOfAnimals != null ? listOfAnimals.equals(zooDTO.listOfAnimals) : zooDTO.listOfAnimals == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (listOfAnimals != null ? listOfAnimals.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ZooDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", listOfAnimals=" + listOfAnimals +
                '}';
    }
}
