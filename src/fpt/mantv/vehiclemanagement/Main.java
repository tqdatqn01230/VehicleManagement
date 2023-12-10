package fpt.mantv.vehiclemanagement;

import fpt.mantv.vehiclemanagement.dao.FileDAO;
import fpt.mantv.vehiclemanagement.dao.impl.VehicleDaoDefault;
import fpt.mantv.vehiclemanagement.entities.Vehicle;
import fpt.mantv.vehiclemanagement.helper.Menu;
import fpt.mantv.vehiclemanagement.repositories.VehicleRepository;
import fpt.mantv.vehiclemanagement.repositories.impl.VehicleRepositoryImpl;
import fpt.mantv.vehiclemanagement.services.VehicleService;
import fpt.mantv.vehiclemanagement.services.impl.VehicleServiceImpl;


public class Main {
    private static final String FILE_NAME = "vehicle.dat";
    private static final FileDAO<Vehicle> vehicleDAO = new VehicleDaoDefault(FILE_NAME);
    private static final VehicleRepository vehicleRepository = new VehicleRepositoryImpl(vehicleDAO);
    private static final VehicleService vehicleService = new VehicleServiceImpl(vehicleRepository);
    public static void main(String[] args) {
        Menu mainMenu = new Menu("\nVehicle Management", false);
        mainMenu.add("Add new vehicle");
        mainMenu.add("Check to exist Vehicle");
        mainMenu.add("Update vehicle");
        mainMenu.add("Delete Vehicle");
        mainMenu.add("Search vehicle");
        mainMenu.add("Display vehicle list");
        mainMenu.add(":  Save data to file");
        Menu searchMenu = new Menu("Search  vehicle" , true);
        searchMenu.add("Search By Name");
        searchMenu.add("Search By Id");
        Menu displayMenu = new Menu("Display Vehicle", true);
        displayMenu.add("Show all");
        displayMenu.add("Show all desc by price");

        int choice;
        do {
            choice = mainMenu.getUserChoice();
            switch (choice) {
                case 1: VehicleService.execute(vehicleService::addVehicle, "Continue adding vehicle!");  break;
                case 2: VehicleService.execute(vehicleService::checkExistVehicle, "Continue checking exist vehicle!"); break;
                case 3: VehicleService.execute(vehicleService::updateVehicle, "Continue updating vehicle!"); break;
                case 4: VehicleService.execute(vehicleService::deleteVehicle, "Continue deleting vehicle!"); break;
                case 5: searchMenu(searchMenu, new Runnable[]{vehicleService::findByName, vehicleService::findById}); break;
                case 6: searchMenu(displayMenu, new Runnable[]{vehicleService::showAll, vehicleService::showAllOrderByNameDesc}); break;
                case 7: vehicleService.save();break;
            }
        }while (choice > 0 && choice <= mainMenu.size());

    }
    public static void searchMenu(Menu menu, Runnable[] functions) {
        int choice;
        do {
            choice = menu.getUserChoice();
            if (choice > 0 && choice <= menu.size()) {
                functions[choice -1 ].run();
            } else {
                break;
            }
        }while (true);
    }
}