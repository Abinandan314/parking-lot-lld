package com.parkinglot.service;

import com.parkinglot.model.ParkingLot;
import com.parkinglot.model.Slot;
import com.parkinglot.model.Vehicle;

public class ParkingService {
    private ParkingLot parkingLot;

    public ParkingService(){}

    public void createParkingLot(Integer parkingLotSize){
        if (parkingLotSize == 0){
            System.out.println("Invalid Parking Lot Size");
        }
        this.parkingLot = new ParkingLot(parkingLotSize);
        System.out.println("Parking Lot Created");
    }

    public Slot createSlotForVehicle(Vehicle vehicle,Integer slotNumber){
        return new Slot(slotNumber,vehicle);
    }

    public void parkVehicle(String registrationNumber, String color){
        Vehicle vehicle = new Vehicle(registrationNumber,color);
        Integer availableSlotNumber = this.parkingLot.getBestAvailableSlot();

        this.parkingLot.parkVehicleInSlot(createSlotForVehicle(vehicle,availableSlotNumber));
    }

    public void unParkVehicle(Integer slotNumber){
        this.parkingLot.unParkVehicle(slotNumber);
    }

    public void displayAllParkedCars(){
        System.out.println("Slot Number => Registration Number => Color");
        for (var entry : this.parkingLot.getParkedSlots().entrySet()){
            System.out.println(entry.getKey() + " => " + entry.getValue().getVehicle().getRegistrationNumber()+ " => " + entry.getValue().getVehicle().getColor());
        }
    }
}
