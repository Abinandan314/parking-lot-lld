package com.parkingLot.service;
import com.parkingLot.model.*;
import com.parkingLot.utils.ParkingUtils;
import lombok.AllArgsConstructor;

import java.util.Objects;

public class ParkingService {
    private ParkingLot parkingLot;
    static ParkingService parkingService = null;

    ParkingService(ParkingLot parkingLot){
        this.parkingLot = parkingLot;
    }

    public static ParkingService getInstance(ParkingLot parkingLot){
        if (Objects.isNull(parkingService)){
            parkingService = new ParkingService(parkingLot);
        }
        return parkingService;
    }

    public ParkingTicket parkVehicle(Vehicle vehicle){
        ParkingSlot.ParkingSlotType parkingSlotType = ParkingUtils.getParkingSlotTypeForVehicleType(vehicle.getVehicleType());
        ParkingFloor parkingFloor = parkingLot.getAvailableParkingFloor(parkingSlotType);

        if (Objects.isNull(parkingFloor)){
            return null;
        }
//        System.out.println("SuccessFully Parked Vehicle");
        return parkingFloor.parkVehicle(vehicle,parkingSlotType);
    }

    public void unParkVehicle(ParkingTicket parkingTicket){
        ParkingFloor parkingFloor = parkingLot.getParkingFloors().getOrDefault(parkingTicket.getParkingFloorId(),null);
        if (Objects.isNull(parkingFloor)){
//            System.out.println("Invalid Parking Ticket");
            return;
        }
        parkingFloor.unParkVehicle(parkingTicket);

        System.out.println("Vehicle UnParked Successfully");
    }
}
