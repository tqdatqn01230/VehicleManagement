package fpt.mantv.vehiclemanagement.repositories.impl;

import fpt.mantv.vehiclemanagement.dao.FileDAO;
import fpt.mantv.vehiclemanagement.entities.Vehicle;
import fpt.mantv.vehiclemanagement.repositories.VehicleRepository;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class VehicleRepositoryImpl implements VehicleRepository {
    private FileDAO<Vehicle> vehicleDAO;
    private List<Vehicle> vehicles;

    public VehicleRepositoryImpl(FileDAO<Vehicle> vehicleDAO) {
        this.vehicleDAO = vehicleDAO;
        try {
            vehicles = vehicleDAO.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        Optional<Vehicle> optionalVehicle = vehicles.stream().max(Comparator.comparingInt(Vehicle::getId));
        if (optionalVehicle.isPresent()) {
            vehicle.setId(optionalVehicle.get().getId() + 1);
        } else {
            vehicle.setId(1);
        }
        vehicles.add(vehicle);
    }

    @Override
    public boolean checkExistVehicle(int id) {
        return vehicles.stream().anyMatch(vehicle -> vehicle.getId() == id);
    }

    @Override
    public void updateVehicle() {

    }

    @Override
    public void deleteVehicle(int id) {
        vehicles = vehicles.stream()
                .filter(v -> v.getId() != id)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Vehicle> findById(int id) {
        return vehicles.stream()
                .filter(v -> v.getId() == id)
                .findFirst();
    }

    @Override
    public List<Vehicle> findByName(String name) {
        return vehicles.stream()
                .filter(v -> v.getName().toLowerCase().contains(name.toLowerCase()))
                .sorted((v1,v2) -> v2.getName().compareTo(v1.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> showAll() {
        return vehicles;
    }

    @Override
    public List<Vehicle> showAllOrderByPriceDesc() {
        return vehicles.stream()
                .sorted((v1,v2) -> v2.getPrice().compareTo(v1.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public void saveData() throws IOException {
        vehicleDAO.write(vehicles);
    }
}
