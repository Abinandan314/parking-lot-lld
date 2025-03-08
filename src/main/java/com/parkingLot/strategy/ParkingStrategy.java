package com.parkingLot.strategy;

import com.parkingLot.model.ParkingSlot;

public interface ParkingStrategy {
    void addParkingSlot(ParkingSlot parkingSlot);
    ParkingSlot getBestSlotForParking(ParkingSlot.ParkingSlotType parkingSlotType);
    void unParkVehicle(ParkingSlot parkingSlot);
}
