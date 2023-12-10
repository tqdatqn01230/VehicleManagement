package fpt.mantv.vehiclemanagement.repositories;

import fpt.mantv.vehiclemanagement.entities.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends BaseRepository{
    void addVehicle(Vehicle vehicle);
    boolean checkExistVehicle(int id);
    void updateVehicle();
    void deleteVehicle(int id);
    Optional<Vehicle> findById(int id);
    List<Vehicle> findByName(String name);
    List<Vehicle> showAll();
    List<Vehicle> showAllOrderByPriceDesc();
}
