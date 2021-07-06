package Exercise.service.impl;

import Exercise.entities.Parcel;
import Exercise.entities.Truck;
import Exercise.service.TruckService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TruckServiceImpl implements TruckService {

    private List <Truck> trucks = new ArrayList();

    public List<Truck> getTrucks() {
        return trucks;
    }

    public void saveTruck(Truck theTruck) {
        trucks.add(theTruck);
    }

    public void updateTruck(Truck theTruck) {
        trucks.removeIf(truck -> truck.getId().equals(theTruck.getId()));
        trucks.add(theTruck);
    }

    public Truck getTruck(Integer theId) {
        return trucks.stream().filter(truck -> truck.getId().equals(theId)).findAny().orElse(null);
    }

    public void deleteTruck(Integer theId) {
        trucks.removeIf((Truck truck) -> truck.getId().equals(theId));
    }

    public void loadTheTruck(Integer truckId, Parcel parcel) {
        Truck theTruck = trucks.stream().filter(truck -> truck.getId().equals(truckId)).findAny().orElse(null);
        List <Parcel> theParcels = theTruck.getParcels();
        theParcels.add(parcel);
        theTruck.setParcels(theParcels);
    }

    public void unloadTheTruck(Integer truckId, Integer parcelId) {
        Truck theTruck = trucks.stream().filter(truck -> truck.getId().equals(truckId)).findAny().orElse(null);
        List <Parcel> theParcels = theTruck.getParcels();
        theParcels.removeIf(parcel -> parcel.getId().equals(parcelId));
        theTruck.setParcels(theParcels);
    }

}
