package Exercise.Service;

import Exercise.Entity.Parcel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Component("parcelServiceImpl")
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

    public Parcel getParcel(Integer theId) {
        Parcel theParcel = parcels.stream().filter(parcel -> parcel.getId().equals(theId)).findAny().orElse(null);
        return theParcel;
    }

    public void deleteParcel(Integer theId) {
        parcels.removeIf((Parcel parcel) -> parcel.getId() == theId);

    }
}
