package Exercise.Service;

import Exercise.Entity.Parcel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Component("availableParcelServiceImpl")
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

    public Parcel getParcel(Integer theId) {
        Parcel theParcel = availableParcels.stream().filter(parcel -> parcel.getId().equals(theId)).findAny().orElse(null);
        return theParcel;
    }

    public void deleteParcel(Integer theId) {
        availableParcels.removeIf((Parcel parcel) -> parcel.getId() == theId);
    }
}
