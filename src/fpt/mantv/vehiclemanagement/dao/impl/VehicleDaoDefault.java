package fpt.mantv.vehiclemanagement.dao.impl;


import fpt.mantv.vehiclemanagement.dao.FileDAO;
import fpt.mantv.vehiclemanagement.entities.Vehicle;

import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//Using for bytes file
public class VehicleDaoDefault implements FileDAO<Vehicle> {
    private String fileName;
    public VehicleDaoDefault(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Vehicle> read() throws  IOException {
        List<Vehicle> vehicles = new ArrayList<>();
        ObjectInputStream ois =null ;
        try{

            Vehicle vehicle;
            try{
                ois = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)));
                while ((vehicle = ((Vehicle) ois.readObject())) != null ){
                    vehicles.add(vehicle);
                }
            } catch (EOFException | ClassNotFoundException e){
            }

        }
        finally {
            if (ois != null) ois.close();
        }
        return vehicles;
    }

    @Override
    public void write(List<Vehicle> vehicles) throws  IOException {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(fileName);
            oos = new ObjectOutputStream(fos);
            for (Vehicle vehicle: vehicles){
                oos.writeObject(vehicle);
            }
        } finally {
            assert fos != null;
            assert oos != null;
            oos.close();
            fos.close();
        }
    }
}
