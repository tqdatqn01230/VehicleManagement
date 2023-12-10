package fpt.mantv.vehiclemanagement.services;

import fpt.mantv.vehiclemanagement.helper.ScannerHelper;


public interface VehicleService {
    void addVehicle();

    void checkExistVehicle();

    void updateVehicle();

    void deleteVehicle();

    void findById();

    void findByName();

    void showAll();

    void showAllOrderByNameDesc();
    void save();

    static void execute(Runnable runnable, String msg) {
        while (true) {
            runnable.run();
            if (ScannerHelper.getBoolean("Do you you want to go back to main menu?")) {
                return;
            } else {
                System.out.println(msg);
            }
        }
    }
}
