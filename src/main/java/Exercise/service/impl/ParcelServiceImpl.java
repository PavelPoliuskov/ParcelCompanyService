package Exercise.service.impl;

import Exercise.entities.Parcel;
import Exercise.service.ParcelService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParcelServiceImpl implements ParcelService {

    private List <Parcel> parcels = new ArrayList();


    public List<Parcel> getParcels() {
        return parcels;
    }

    public void saveParcel(Parcel theParcel) {
        parcels.add(theParcel);
    }

    public void updateParcel(Parcel theParcel) {
        parcels.removeIf(parcel -> parcel.getId().equals(theParcel.getId()));
        parcels.add(theParcel);

    }

    public Parcel getParcel(Integer parcelId) {
        return parcels.stream().filter(parcel -> parcel.getId().equals(parcelId)).findAny().orElse(null);
    }

    public void deleteParcel(Integer parcelId) {
        parcels.removeIf((Parcel parcel) -> parcel.getId().equals(parcelId));

    }
}
