package Exercise.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class Truck {

    private Integer id;
    private int weight;
    private String startingPoint;
    private String destination;
    private List <Parcel> parcels = new ArrayList<>();

    public int getNumberOfParcels (){
        return parcels.size();
    }

    public int getWeight() {
        weight = 0;
        for (Parcel parcel : parcels){
            weight += parcel.getWeight();
        }
        return weight;
    }

    public Truck(Integer id, int weight, String startingPoint, String destination) {
        this.id = id;
        this.weight = weight;
        this.startingPoint = startingPoint;
        this.destination = destination;
    }

}
