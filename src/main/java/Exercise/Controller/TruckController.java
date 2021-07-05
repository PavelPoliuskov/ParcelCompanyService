package Exercise.Controller;

import Exercise.Entity.Parcel;
import Exercise.Entity.Truck;
import Exercise.Service.AvailableParcelServiceImpl;
import Exercise.Service.ParcelService;
import Exercise.Service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/showFormForUpdate")
public class TruckController {

    @Autowired
    private TruckService truckService;
    @Autowired
    @Qualifier("parcelServiceImpl")
    private ParcelService parcelServiceImpl;
    @Autowired
    @Qualifier("availableParcelServiceImpl")
    private AvailableParcelServiceImpl availableParcelService;

    @GetMapping
    public String showFormForUpdate (@RequestParam("truckId") Integer theId, Model model) {
        Truck theTruck = truckService.getTruck(theId);
        model.addAttribute("truck", theTruck);
        List<Parcel> theParcels = truckService.getTruck(theId).getParcels();
        model.addAttribute("theParcels", theParcels);
        List <Parcel> theAvailableParcels = availableParcelService.getParcels();
        model.addAttribute("availableParcels", theAvailableParcels);

        return "update-truck";
    }

    @GetMapping("{truckId}/truckLoad")
    public String loadParcel(@PathVariable("truckId") Integer truckId, @RequestParam("parcelId") Integer parcelId, Model model) {
        Truck theTruck = truckService.getTruck(truckId);
        model.addAttribute("truck", theTruck);
        List<Parcel> theParcels = truckService.getTruck(truckId).getParcels();
        model.addAttribute("theParcels", theParcels);
        List <Parcel> theAvailableParcels = availableParcelService.getParcels();
        model.addAttribute("availableParcels", theAvailableParcels);

        truckService.loadTheTruck(truckId, parcelServiceImpl.getParcel(parcelId));
        availableParcelService.deleteParcel(parcelId);

        System.out.println("number of parcels: " + theParcels.size());

        return "update-truck";
    }

    @GetMapping("{truckId}/delivered")
    public String deliveredParcel(@PathVariable("truckId") Integer truckId, @RequestParam("parcelId") Integer parcelId, Model model) {
        Truck theTruck = truckService.getTruck(truckId);
        model.addAttribute("truck", theTruck);
        List<Parcel> theParcels = truckService.getTruck(truckId).getParcels();
        model.addAttribute("theParcels", theParcels);
        List <Parcel> theAvailableParcels = availableParcelService.getParcels();
        model.addAttribute("availableParcels", theAvailableParcels);

        parcelServiceImpl.deleteParcel(parcelId);
        truckService.unloadTheTruck(truckId, parcelServiceImpl.getParcel(parcelId));
        return "update-truck";
    }

    @GetMapping("{truckId}/undelivered")
    public String undeliveredParcel(@PathVariable("truckId") Integer truckId, @RequestParam("parcelId") Integer parcelId, Model model) {
        Truck theTruck = truckService.getTruck(truckId);
        model.addAttribute("truck", theTruck);
        List<Parcel> theParcels = truckService.getTruck(truckId).getParcels();
        model.addAttribute("theParcels", theParcels);
        List <Parcel> theAvailableParcels = availableParcelService.getParcels();
        model.addAttribute("availableParcels", theAvailableParcels);

        availableParcelService.saveParcel(parcelServiceImpl.getParcel(parcelId));
        truckService.unloadTheTruck(truckId, parcelServiceImpl.getParcel(parcelId));
        return "update-truck";
    }

}
