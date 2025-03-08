package com.parkingLot.strategy;

import com.parkingLot.model.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class NearEntryParkingStrategy implements ParkingStrategy{
    //floorId -> {slotType - > {DistanceFromEntry, Slot}}
    Map<String, Map<ParkingSlot.ParkingSlotType, PriorityQueue<SlotPair>>> parkingSlotMap;
    Map<String, Map<String,ParkingSlot>> bookedSlots;

    public NearEntryParkingStrategy() {
        this.parkingSlotMap = new HashMap<>();
        this.bookedSlots = new HashMap<>();
    }

    @Override
    public ParkingSlot parkVehicle(ParkingFloor parkingFloor, Vehicle vehicle, ParkingSlot.ParkingSlotType parkingSlotType) {
        var floorMap = parkingSlotMap.get(parkingFloor.getFloorId());

        if (floorMap.isEmpty() || floorMap.containsKey(parkingSlotType)){
            System.out.println("No slots available");
            return null;
        }
        var parkingSlot = Objects.requireNonNull(floorMap.get(parkingSlotType).poll()).getParkingSlot();
        parkingSlot.setVehicle(vehicle);
        bookedSlots.computeIfAbsent(
                parkingFloor.getFloorId(), k -> new HashMap<>()
        ).put(parkingSlot.getId(),parkingSlot);
        return parkingSlot;
    }

    @Override
    public void unParkVehicle(ParkingFloor parkingFloor, ParkingSlot parkingSlot) {
        var slotPairPriorityQueue = parkingSlotMap.computeIfAbsent(parkingFloor.getFloorId(),k->new HashMap<>()).computeIfAbsent(parkingSlot.getParkingSlotType(),k->new PriorityQueue<>());
        var bookedSlotsMap = bookedSlots.get(parkingFloor.getFloorId());
        bookedSlotsMap.remove(parkingSlot.getId());
        slotPairPriorityQueue.add(SlotPair.builder().parkingSlot(parkingSlot).distance(parkingSlot.getDistanceFromEntry()).build());
    }

    @Override
    public void addParkingSlot(ParkingSlot parkingSlot, ParkingFloor parkingFloor) {
        var parkingFloorMap = parkingSlotMap.computeIfAbsent(
                parkingFloor.getFloorId(),
                k -> new HashMap<>()
        );

        addParkingSlotInPriorityQueueMapForGivenFloor(parkingFloorMap, parkingSlot);
    }



    private void addParkingSlotInPriorityQueueMapForGivenFloor(Map<ParkingSlot.ParkingSlotType,PriorityQueue<SlotPair>> parkingSlotTypePriorityQueueMap, ParkingSlot parkingSlot){

        SlotPair slotPair = SlotPair.builder().parkingSlot(parkingSlot).distance(parkingSlot.getDistanceFromEntry()).build();

        parkingSlotTypePriorityQueueMap
                .computeIfAbsent(parkingSlot.getParkingSlotType(), k -> new PriorityQueue<>())
                .add(slotPair);
    }
}
