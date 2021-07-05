package Exercise.Entity;

public class Parcel {

    private Integer id;
    private Integer weight;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Parcel(Integer id, Integer weight) {
        this.id = id;
        this.weight = weight;
    }

    public Parcel() {
    }
}
