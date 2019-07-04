package hu.eteosf.vivien.zoomanager.model;

public class AnimalDTO {
    private Long id;
    private String name;
    private Long age;
    private String color;

    public AnimalDTO(Long id, String name, Long age, String color) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public AnimalDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

        AnimalDTO animalDTO = (AnimalDTO) o;

        if (id != null ? !id.equals(animalDTO.id) : animalDTO.id != null) return false;
        if (name != null ? !name.equals(animalDTO.name) : animalDTO.name != null) return false;
        if (age != null ? !age.equals(animalDTO.age) : animalDTO.age != null) return false;
        return color != null ? color.equals(animalDTO.color) : animalDTO.color == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AnimalDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                '}';
    }
}
