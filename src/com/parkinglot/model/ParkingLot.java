package com.parkinglot.model;

import java.util.*;

public class ParkingLot {
    Integer parkingLotSize;
    TreeMap<Integer, Slot> parkedSlots;
    TreeSet<Integer> availableSlots;

    public ParkingLot(Integer parkingLotSize){
        this.parkingLotSize = parkingLotSize;
        this.parkedSlots = new TreeMap<>();
        this.availableSlots = new TreeSet<>();
        fillAvailableSlots(parkingLotSize);
    }

    private void fillAvailableSlots(Integer parkingLotSize){
        for (int slot = 1;slot<=parkingLotSize;slot++){
            availableSlots.add(slot);
        }
    }

    public Integer getParkingLotSize() {
        return parkingLotSize;
    }

    public void setParkingLotSize(Integer parkingLotSize) {
        this.parkingLotSize = parkingLotSize;
    }

    public Map<Integer, Slot> getParkedSlots() {
        return parkedSlots;
    }

    public void setParkedSlots(TreeMap<Integer, Slot> parkedSlots) {
        this.parkedSlots = parkedSlots;
    }

    public boolean isParkingAvailable(){
        return this.parkedSlots.size() < this.parkingLotSize;
    }
    public void parkVehicleInSlot(Slot s){
        if (!isParkingAvailable()){
            System.out.println("Parking Full");
            return;
        }
        parkedSlots.put(s.getSlotNumber(), s);
        availableSlots.remove(s.getSlotNumber());
        System.out.println("Vehicle Parked at slot " + s.getSlotNumber());
    }
    public void unParkVehicle(Integer slotNumber){
        Slot slot = parkedSlots.get(slotNumber);
        if (Objects.isNull(slot)){
            System.out.println("Invalid Slot");
            return;
        }
        parkedSlots.remove(slotNumber);
        availableSlots.add(slotNumber);
        System.out.println("Vehicle Un parked");
    }

    public Integer getBestAvailableSlot(){
        return availableSlots.first();
    }


}
