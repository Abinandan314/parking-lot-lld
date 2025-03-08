package com.parkingLot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ParkingFloor {
    String floorId;
    Map<String,ParkingSlot> parkedSlots;
    Map<ParkingSlot.ParkingSlotType, PriorityQueue<ParkingSlot>> availableSlots; //denotes floor wise parking slots

    public ParkingFloor(String floorId){
        this.floorId = floorId;
        this.parkedSlots = new HashMap<>();
        this.availableSlots = new HashMap<>();
    }

    public void addParkingSlotsToFloor(ParkingSlot parkingSlot){
        availableSlots.computeIfAbsent(parkingSlot.getParkingSlotType(), k -> new PriorityQueue<>()).add(parkingSlot);
    }

    public ParkingSlot getBestParkingSpot(ParkingSlot.ParkingSlotType parkingSlotType){
        var priorityQueue = availableSlots.get(parkingSlotType);

        return priorityQueue.poll();
    }

    private ParkingSlot.ParkingSlotType getParkingSlotTypeForVehicleType(Vehicle.VehicleType vehicleType){
        if (vehicleType.equals(Vehicle.VehicleType.CAR)) return ParkingSlot.ParkingSlotType.CAR;
        if (vehicleType.equals(Vehicle.VehicleType.TWO_WHEELER)) return ParkingSlot.ParkingSlotType.TWO_WHEELER;

        return ParkingSlot.ParkingSlotType.COMPACT;
    }

    public ParkingTicket parkVehicle(Vehicle vehicle, ParkingSlot.ParkingSlotType parkingSlotType){

        ParkingSlot parkingSlot = getBestParkingSpot(parkingSlotType);

        parkingSlot.setVehicle(vehicle);

        parkedSlots.put(parkingSlot.getId(),parkingSlot);

        return ParkingTicket.builder().id(UUID.randomUUID().toString()).parkingSlotId(parkingSlot.getId()).parkingSlotType(parkingSlotType.name()).parkingFloorId(floorId).build();
    }

    public void unParkVehicle(ParkingTicket parkingTicket){
        ParkingSlot parkingSlot = parkedSlots.get(parkingTicket.getParkingSlotId());

        parkedSlots.remove(parkingTicket.getId());

        availableSlots.computeIfAbsent(
                parkingSlot.getParkingSlotType(),k -> new PriorityQueue<>()
        ).add(parkingSlot);
    }
}
