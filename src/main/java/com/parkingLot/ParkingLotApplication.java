package com.parkingLot;


import com.parkingLot.handlers.CustomInputHandler;
import com.parkingLot.model.*;
import com.parkingLot.service.ParkingService;
import com.parkingLot.strategy.NearEntryParkingStrategy;
import com.parkingLot.strategy.ParkingStrategy;

import java.io.IOException;

public class ParkingLotApplication {

    public static void main(String[] args) throws IOException {
        ParkingSlot parkingSlot1 = ParkingSlot.builder().id("slot1").parkingSlotType(ParkingSlot.ParkingSlotType.CAR).distanceFromEntry(0.0).build();
        ParkingSlot parkingSlot2 = ParkingSlot.builder().id("slot2").parkingSlotType(ParkingSlot.ParkingSlotType.CAR).distanceFromEntry(0.5).build();
        ParkingStrategy parkingStrategy = new NearEntryParkingStrategy();

        ParkingFloor parkingFloor = new ParkingFloor("Floor1",parkingStrategy);
        parkingFloor.addParkingSlotsToFloor(parkingSlot1);
        parkingFloor.addParkingSlotsToFloor(parkingSlot2);

        ParkingLot parkingLot = ParkingLot.getParkingLot("Abi's Parking",50);

        ParkingService parkingService = ParkingService.getInstance(parkingLot);

        while (true){
            System.out.println("Welcome to Parking Lot Application");
            System.out.println("1. Add Floor");
            System.out.println("2. Park a Vehicle");
            System.out.println("3. Unpark a vehicle");
            System.out.println("4. Exit");

            Integer choice = CustomInputHandler.getvalidInteger();

            if (choice.equals(1)){
                parkingLot.addFloors(parkingFloor);
            }
            else if(choice.equals(2)){
                System.out.println("Enter Vehicle Registration Number");
                String id = CustomInputHandler.getValidString();
                Vehicle vehicle = Vehicle.builder().id(id).registeredNumber(id).vehicleType(Vehicle.VehicleType.CAR).build();
                ParkingTicket parkingTicket = parkingService.parkVehicle(vehicle);
                System.out.println(parkingTicket);
            }
            else if (choice.equals(3)){
                System.out.println("Enter Floor Id");
                String floorId = CustomInputHandler.getValidString();
                System.out.println("Enter Slot id");
                String slotId = CustomInputHandler.getValidString();
                parkingService.unParkVehicle(ParkingTicket.builder().parkingFloorId(floorId).parkingSlotId(slotId).build());

            } else if (choice.equals(4)) {
                break;
            }
            else{
                System.out.println("Invalid Operation");
            }
        }

    }
}