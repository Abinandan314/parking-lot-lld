package com.parkingLot.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.TreeMap;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ParkingLot {
    String name;
    TreeMap<String,ParkingFloor> parkingFloors;
    static ParkingLot parkingLot = null;
    Integer capacity;

    public ParkingLot(String name,Integer capacity) {
        this.name = name;
        this.parkingFloors = new TreeMap<>();
        this.capacity = capacity;
    }

    // Following SingleTon Design Pattern to allow initiating only once instance of Parking Lot.
    public static ParkingLot getParkingLot(String name, Integer capacity) {
        if (parkingLot == null){
            parkingLot = new ParkingLot(name,capacity);
        }
        return parkingLot;
    }

    public void addFloors(ParkingFloor parkingFloor){
        if (parkingFloors.containsKey(parkingFloor.getFloorId())){
            System.out.println("Floor Already exists");
            return;
        }
        parkingFloors.put(parkingFloor.getFloorId(),parkingFloor);
    }

    //Also Validates if there's any available slots open.
    public ParkingFloor getAvailableParkingFloor(ParkingSlot.ParkingSlotType parkingSlotType){
        for (var parkingFloor :  parkingFloors.values()){
            if (!parkingFloor.getAvailableSlots().get(parkingSlotType).isEmpty()){
                return parkingFloor;
            }
        }
        System.out.println("No Slots are available for : " + parkingSlotType.name());
        return null;
    }
}
