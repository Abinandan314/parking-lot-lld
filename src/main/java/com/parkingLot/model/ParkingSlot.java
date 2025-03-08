package com.parkingLot.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ParkingSlot implements Comparable<ParkingSlot>{
    String id;
    Vehicle vehicle;
    ParkingSlotType parkingSlotType;
    Double distanceFromEntry;

    @Override
    public int compareTo(ParkingSlot otherParkingSlot) {
        return Double.compare(distanceFromEntry,otherParkingSlot.getDistanceFromEntry());
    }

    public enum ParkingSlotType{
        COMPACT,
        CAR,
        TWO_WHEELER
    }
}
