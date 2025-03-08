package com.parkingLot.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Vehicle {
    String id;
    String registeredNumber;
    VehicleType vehicleType;

    public enum VehicleType {
        COMPACT_CAR,
        CAR,
        TWO_WHEELER
    }
}
