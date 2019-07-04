package hu.eteosf.vivien.zoomanager.repository;

import javax.persistence.*;

@Entity
@Table(name = "animal")
public class AnimalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private Long age;
    @Column(name = "color")
    private String color;
    @Column(name = "ownid", unique = true)
    private Long ownid;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "zoo_id")
    private ZooEntity zooEntity;

    public AnimalEntity(Long id, String name, Long age, String color) {
        this.ownid = id;
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public AnimalEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwnid() {
        return ownid;
    }

    public void setOwnid(Long ownid) {
        this.ownid = ownid;
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

    public ZooEntity getZooEntity() {
        return zooEntity;
    }

    public void setZooEntity(ZooEntity zooEntity) {
        this.zooEntity = zooEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnimalEntity that = (AnimalEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (age != null ? !age.equals(that.age) : that.age != null) return false;
        if (color != null ? !color.equals(that.color) : that.color != null) return false;
        if (ownid != null ? !ownid.equals(that.ownid) : that.ownid != null) return false;
        return zooEntity != null ? zooEntity.equals(that.zooEntity) : that.zooEntity == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (ownid != null ? ownid.hashCode() : 0);
        result = 31 * result + (zooEntity != null ? zooEntity.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AnimalEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                ", ownid=" + ownid +
                ", zooEntity=" + zooEntity +
                '}';
    }
}
