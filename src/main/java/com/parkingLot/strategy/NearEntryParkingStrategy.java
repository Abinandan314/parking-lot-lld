package com.parkingLot.strategy;

import com.parkingLot.model.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class NearEntryParkingStrategy implements ParkingStrategy{
    //floorId -> {slotType - > {DistanceFromEntry, Slot}}
    Map<ParkingSlot.ParkingSlotType, PriorityQueue<ParkingSlot>> parkingSlotMap;

    public NearEntryParkingStrategy() {
        this.parkingSlotMap = new HashMap<>();
    }

    @Override
    public ParkingSlot getBestSlotForParking(ParkingSlot.ParkingSlotType parkingSlotType) {
        var parkingSlots = parkingSlotMap.get(parkingSlotType);

        if (parkingSlots.isEmpty()){
            System.out.println("No slots available");
            return null;
        }

        return parkingSlots.poll();

    }

    @Override
    public void unParkVehicle(ParkingSlot parkingSlot) {
        parkingSlotMap.computeIfAbsent(parkingSlot.getParkingSlotType(), k -> new PriorityQueue<>()).add(parkingSlot);
    }

    @Override
    public void addParkingSlot(ParkingSlot parkingSlot) {
        parkingSlotMap.computeIfAbsent(parkingSlot.getParkingSlotType(),k -> new PriorityQueue<>()).add(parkingSlot);
    }
}
