package Exercise.service;

import Exercise.entities.Parcel;
import Exercise.entities.Truck;

import java.util.List;

public interface TruckService {

    public List<Truck> getTrucks();

    public void saveTruck (Truck theTruck);

    public void updateTruck (Truck theTruck);

    public Truck getTruck (Integer theId);

    public void deleteTruck (Integer theId);

    public void loadTheTruck (Integer theId, Parcel parcel);

    public void unloadTheTruck(Integer theId, Parcel parcel);

}
