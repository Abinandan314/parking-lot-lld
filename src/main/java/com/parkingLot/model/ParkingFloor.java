package com.parkingLot.model;

import com.parkingLot.strategy.ParkingStrategy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ParkingFloor {
    String floorId;
    Map<String,ParkingSlot> parkingSlots;

    ParkingStrategy parkingStrategy;

    public ParkingFloor(String floorId){
        this.floorId = floorId;
        this.parkingSlots = new HashMap<>();
    }

    public ParkingFloor(String floorId,ParkingStrategy parkingStrategy){
        this.floorId = floorId;
        this.parkingSlots = new HashMap<>();
        this.parkingStrategy = parkingStrategy;
    }

    public void addParkingSlotsToFloor(ParkingSlot parkingSlot){
        parkingSlots.put(parkingSlot.getId(), parkingSlot);
        parkingStrategy.addParkingSlot(parkingSlot);
    }

    public ParkingTicket parkVehicle(Vehicle vehicle, ParkingSlot.ParkingSlotType parkingSlotType){
        ParkingSlot parkingSlot = parkingStrategy.getBestSlotForParking(parkingSlotType);

        parkingSlot.setVehicle(vehicle);
        parkingSlot.setIsOccupied(true);

        return ParkingTicket.builder().id(UUID.randomUUID().toString()).parkingFloorId(floorId).parkingSlotId(parkingSlot.getId()).build();
    }

    public void unParkVehicle(ParkingTicket parkingTicket){
        ParkingSlot parkingSlot = parkingSlots.get(parkingTicket.getParkingSlotId());

        if (Objects.isNull(parkingSlot)){
            System.out.println("This is not a valid parking slot id");
            return;
        }
        parkingSlot.setVehicle(null);
        parkingSlot.setIsOccupied(false);
        parkingStrategy.unParkVehicle(parkingSlot);

    }

}
