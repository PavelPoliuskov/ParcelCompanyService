package Exercise.entities;

import java.util.ArrayList;
import java.util.List;

public class Truck {

    private Integer id;
    private int weight;
    private String startingPoint;
    private String destination;
    private List <Parcel> parcels = new ArrayList<>();

    public int getNumberOfParcels (){
        return parcels.size();
    }
    public List<Parcel> getParcels() {
        return parcels;
    }

    public void setParcels(List<Parcel> parcels) {
        this.parcels = parcels;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getWeight() {
        weight = 0;
        for (Parcel parcel : parcels){
            weight += parcel.getWeight();
        }
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(String startingPoint) {
        this.startingPoint = startingPoint;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Truck(Integer id, int weight, String startingPoint, String destination) {
        this.id = id;
        this.weight = weight;
        this.startingPoint = startingPoint;
        this.destination = destination;
    }

    public Truck() {
    }
}
