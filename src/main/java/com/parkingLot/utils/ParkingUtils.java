package com.parkingLot.utils;

import com.parkingLot.model.ParkingSlot;
import com.parkingLot.model.Vehicle;

public class ParkingUtils {
    public static ParkingSlot.ParkingSlotType getParkingSlotTypeForVehicleType(Vehicle.VehicleType vehicleType){
        if (vehicleType.equals(Vehicle.VehicleType.CAR)) return ParkingSlot.ParkingSlotType.CAR;
        if (vehicleType.equals(Vehicle.VehicleType.TWO_WHEELER)) return ParkingSlot.ParkingSlotType.TWO_WHEELER;

        return ParkingSlot.ParkingSlotType.COMPACT;
    }
}
