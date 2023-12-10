package fpt.mantv.vehiclemanagement.services.impl;

import fpt.mantv.vehiclemanagement.entities.Vehicle;
import fpt.mantv.vehiclemanagement.helper.RegexHelper;
import fpt.mantv.vehiclemanagement.helper.ScannerHelper;
import fpt.mantv.vehiclemanagement.repositories.VehicleRepository;
import fpt.mantv.vehiclemanagement.services.VehicleService;

import java.io.IOException;
import java.time.Year;
import java.util.List;
import java.util.Optional;

public class VehicleServiceImpl implements VehicleService {
    private VehicleRepository vehicleRepository;
    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }
    @Override
    public void addVehicle() {
        Vehicle vehicle = new Vehicle();
        getVehicleInput(vehicle, false);
        vehicleRepository.addVehicle(vehicle);
        System.out.println("Create Vehicle Success!");
    }

    @Override
    public void checkExistVehicle() {
        int id =  getId();
        if (vehicleRepository.checkExistVehicle(id)) {
            System.out.println("Exist Vehicle!");
        } else {
            System.out.println("No Vehicle Found!");
        }
    }

    @Override
    public void updateVehicle() {
        int id = getId();
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        if (optionalVehicle.isPresent()){
            getVehicleInput(optionalVehicle.get(), true);
            System.out.println("Update Success!");
            System.out.println(optionalVehicle.get());
        } else {
            System.out.println("Vehicle does not exist");
        }
    }

    @Override
    public void deleteVehicle() {
        int id = getId();
        if (vehicleRepository.checkExistVehicle(id)) {
            if (ScannerHelper.getBoolean("Do you want to delete vehicle: " + id)) {
                vehicleRepository.deleteVehicle(id);
                System.out.println("Delete Success!");
            } else {
                System.out.println("Delete fail");
            }
        } else {
            System.out.println("No Vehicle Found!");
        }
    }

    @Override
    public void findById() {
        int id = getId();
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        if (optionalVehicle.isPresent()) {
            System.out.println("Vehicle Found!");
            System.out.println(optionalVehicle.get());
        } else {
            System.out.println("Vehicle not found!");
        }
    }

    @Override
    public void findByName() {
        String name = ScannerHelper.getUserInputString(RegexHelper.REGEX_NORMAL,"Enter search name: ","Wrong format");
        List<Vehicle> vehicles = vehicleRepository.findByName(name);
        if (vehicles.isEmpty()) {
            System.out.println("Vehicle not found!");
        } else {
            printList(vehicles);
        }
    }

    @Override
    public void showAll() {
        printList(vehicleRepository.showAll());
    }

    @Override
    public void showAllOrderByNameDesc() {
        printList(vehicleRepository.showAllOrderByPriceDesc());
    }

    @Override
    public void save() {
        try {
            vehicleRepository.saveData();
            System.out.println("Saved Successfully!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void getVehicleInput(Vehicle vehicle,  boolean keepOldValue) {
        vehicle.setName(ScannerHelper.getUserInputString(RegexHelper.REGEX_NORMAL, "Enter Name: " ,"Wrong format!", keepOldValue ? vehicle.getName() : null));
        vehicle.setBrand(ScannerHelper.getUserInputString(RegexHelper.REGEX_NORMAL, "Enter Brand: " ,"Wrong format!", keepOldValue ? vehicle.getBrand() : null));
        vehicle.setColor(ScannerHelper.getUserInputString(RegexHelper.REGEX_COLOR, "Enter Color: " ,"Wrong format!",  keepOldValue ? vehicle.getColor() : null));
        vehicle.setPrice(ScannerHelper.getUserInputDouble("Enter Price: ", "Must be a number", 0, 100000000, keepOldValue ? vehicle.getPrice() : null));
        vehicle.setProductYear(ScannerHelper.getUserInputInt("Enter Production Year: ", "Wrong format", 1950,  Year.now().getValue(), keepOldValue ? vehicle.getProductYear() : null));
    }

    private int getId() {
        return ScannerHelper.getUserInputInt("Enter Vehicle Id: ","Wrong format!", 0, Integer.MAX_VALUE);
    }

    private void printList(List<Vehicle> vehicles) {
        System.out.println("-------------------Vehicle List------------------");
        vehicles.forEach(System.out::println);
    }
}
