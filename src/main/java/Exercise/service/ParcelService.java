package Exercise.service;

import Exercise.entities.Parcel;

import java.util.List;

public interface ParcelService {

    public List<Parcel> getParcels();

    public void saveParcel (Parcel theParcel);

    public void updateParcel (Parcel theParcel);

    public Parcel getParcel (Integer theId);

    public void deleteParcel (Integer theId);

}
