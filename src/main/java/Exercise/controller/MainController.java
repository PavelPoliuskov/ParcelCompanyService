package Exercise.controller;


import Exercise.entities.Parcel;
import Exercise.entities.Truck;
import Exercise.service.impl.AvailableParcelServiceImpl;
import Exercise.service.impl.ParcelServiceImpl;
import Exercise.service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping
public class MainController {

    @Autowired
    private TruckService truckService;
    @Autowired
    @Qualifier("parcelServiceImpl")
    private ParcelServiceImpl parcelServiceImpl;
    @Autowired
    @Qualifier("availableParcelServiceImpl")
    private AvailableParcelServiceImpl availableParcelService;

    @PostConstruct
    private void loadDataForTest() {
        Truck t1 = new Truck(1, 0, "Riga", "London");
        Truck t2 = new Truck(2, 1, "Berlin", "Madrid");
        Truck t3 = new Truck(3, 0, "Rome", "Bucharest");
        Truck t4 = new Truck(4, 0, "Paris", "Vienna");
        Truck t5 = new Truck(5, 0, "Hamburg", "Warsaw");

        truckService.saveTruck(t1);
        truckService.saveTruck(t2);
        truckService.saveTruck(t3);
        truckService.saveTruck(t4);
        truckService.saveTruck(t5);

        Parcel p1 = new Parcel(1, 2);
        Parcel p2 = new Parcel(2, 3);
        Parcel p3 = new Parcel(3, 4);
        Parcel p4 = new Parcel(4, 5);
        Parcel p5 = new Parcel(5, 6);

        parcelServiceImpl.saveParcel(p1);
        parcelServiceImpl.saveParcel(p2);
        parcelServiceImpl.saveParcel(p3);
        parcelServiceImpl.saveParcel(p4);
        parcelServiceImpl.saveParcel(p5);

        availableParcelService.saveParcel(p1);
        availableParcelService.saveParcel(p2);
        availableParcelService.saveParcel(p3);
        availableParcelService.saveParcel(p4);
        availableParcelService.saveParcel(p5);

    }


    @GetMapping
    public String listTrucks(Model model){
        List <Truck> theTrucks = truckService.getTrucks();
        theTrucks.sort(Comparator.comparingInt(Truck::getId));
        model.addAttribute("trucks", theTrucks);

        List <Parcel> theParcels = parcelServiceImpl.getParcels();
        theParcels.sort(Comparator.comparing(Parcel::getId));
        model.addAttribute("parcels", theParcels);

        List <Parcel> theAvailableParcels = availableParcelService.getParcels();
        theAvailableParcels.sort(Comparator.comparing(Parcel::getId));
        model.addAttribute("availableParcels", theAvailableParcels);

        return "main";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){
        Truck theTruck = new Truck();
        model.addAttribute("truck", theTruck);
        return "add-truck";
    }

    @PostMapping("/saveTruck")
    public String saveTruck(@ModelAttribute("truck") Truck theTruck, Model model){
        for(Truck truck : truckService.getTrucks()){
            if(truck.getId().equals(theTruck.getId())){
                model.addAttribute("errorMessage", "Id should be unique");
                return "add-truck";
            }
        }
        truckService.saveTruck(theTruck);
        return "redirect:/";
    }

    @PostMapping("/updateTruck")
    public String updateTruck(@ModelAttribute("truck") Truck theTruck){
        truckService.updateTruck(theTruck);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteTruck(@RequestParam("truckId") Integer theId) {
        truckService.deleteTruck(theId);
        return "redirect:/";
    }

    @GetMapping("/showFormForAddingParcel")
    public String showFormForAddingParcel(Model model){
        Parcel theParcel = new Parcel();
        model.addAttribute("parcel", theParcel);
        return "add-parcel";
    }


    @PostMapping("/saveParcel")
    public String saveParcel(@ModelAttribute("parcel") Parcel theParcel, Model model){
        for (Parcel parcel : parcelServiceImpl.getParcels()){
            if (parcel.getId().equals(theParcel.getId())){
                model.addAttribute("errorMessage", "Id should be unique");
                return "add-parcel";
            }
        }
        parcelServiceImpl.saveParcel(theParcel);
        availableParcelService.saveParcel(theParcel);
        return "redirect:/";
    }

    @PostMapping("/updateParcel")
    public String updateParcel(@ModelAttribute("parcel") Parcel theParcel){
        parcelServiceImpl.updateParcel(theParcel);
        availableParcelService.updateParcel(theParcel);
        for (Truck truck : truckService.getTrucks()){
            for (Parcel parcel : truck.getParcels()){
                if (parcel.getId().equals(theParcel.getId())){
                    parcel.setWeight(theParcel.getWeight());
                }
            }
        }
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdatingParcel")
    public String showFormForUpdatingParcel (@RequestParam("parcelId") Integer theId, Model model) {
        Parcel theParcel = parcelServiceImpl.getParcel(theId);
        model.addAttribute("parcel", theParcel);
        return "update-parcel";
    }

    @GetMapping("/deleteParcel")
    public String deleteParcel(@RequestParam("parcelId") Integer theId) {
        parcelServiceImpl.deleteParcel(theId);
        availableParcelService.deleteParcel(theId);
        truckService.getTrucks().forEach(truck -> truck.getParcels().removeIf((Parcel parcel) -> parcel.getId().equals(theId)));
        return "redirect:/";

    }
}
