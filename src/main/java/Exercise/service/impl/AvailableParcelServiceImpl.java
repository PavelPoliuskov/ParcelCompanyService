package Exercise.service.impl;

import Exercise.entities.Parcel;
import Exercise.service.ParcelService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AvailableParcelServiceImpl implements ParcelService {

    private List <Parcel> availableParcels = new ArrayList();

    public List<Parcel> getParcels() {
        return availableParcels;
    }

    public void saveParcel(Parcel theParcel) {
        availableParcels.add(theParcel);

    }

    public void updateParcel(Parcel theParcel) {
        availableParcels.removeIf(parcel -> parcel.getId().equals(theParcel.getId()));
        availableParcels.add(theParcel);
    }

    public Parcel getParcel(Integer parcelId) {
        return availableParcels.stream().filter(parcel -> parcel.getId().equals(parcelId)).findAny().orElse(null);
    }

    public void deleteParcel(Integer parcelId) {
        availableParcels.removeIf((Parcel parcel) -> parcel.getId().equals(parcelId));
    }
}
