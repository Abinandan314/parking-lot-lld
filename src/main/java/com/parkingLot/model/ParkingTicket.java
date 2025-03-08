package com.parkingLot.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ParkingTicket {
    String id;
    String parkingSlotId;
    String parkingFloorId;

    @Override
    public String toString() {
        return "ParkingTicket{" +
                "id='" + id + '\'' +
                ", parkingSlotId='" + parkingSlotId + '\'' +
                ", parkingFloorId='" + parkingFloorId + '\'' +
                '}';
    }
}
