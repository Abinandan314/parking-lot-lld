package com.parkingLot.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SlotPair implements Comparable<SlotPair>{
    Double distance;
    ParkingSlot parkingSlot;

    @Override
    public int compareTo(SlotPair slotPair) {
        return Double.compare(this.distance, slotPair.distance);
    }
}
