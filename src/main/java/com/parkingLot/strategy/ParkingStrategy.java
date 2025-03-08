package com.parkingLot.strategy;

import com.parkingLot.model.ParkingFloor;
import com.parkingLot.model.ParkingSlot;
import com.parkingLot.model.Vehicle;

public interface ParkingStrategy {
    void addParkingSlot(ParkingSlot parkingSlot, ParkingFloor parkingFloor);
    ParkingSlot parkVehicle(ParkingFloor parkingFloor, Vehicle vehicle, ParkingSlot.ParkingSlotType parkingSlotType);
    void unParkVehicle(ParkingFloor parkingFloor, ParkingSlot parkingSlot);
}
