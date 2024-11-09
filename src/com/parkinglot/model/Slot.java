package com.parkinglot.model;

public class Slot {
    private Integer slotNumber;
    private Vehicle vehicle;

    public Slot(){}

    public Slot(Integer slotNumber, Vehicle vehicle) {
        this.slotNumber = slotNumber;
        this.vehicle = vehicle;
    }

    public Integer getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(Integer slotNumber) {
        this.slotNumber = slotNumber;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
