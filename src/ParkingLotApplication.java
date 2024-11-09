import com.parkinglot.handlers.CustomInputHandler;
import com.parkinglot.service.ParkingService;

import java.io.IOException;

public class ParkingLotApplication {
    public static void main(String[] args) throws IOException {
        ParkingService parkingService = new ParkingService();
        System.out.println("Welcome to A's Parking Lot");
        while (true){
            System.out.println("0. Create Parking Lot");
            System.out.println("1. Park a Car");
            System.out.println("2. Un Park a Car");
            System.out.println("3. Show all Available Cars");
            System.out.println("4. Exit");

            int selectedOption = CustomInputHandler.getvalidInteger();

            if (selectedOption == 0) {
                System.out.println("Enter Parking Lot Size");
                Integer parkingLotSize = CustomInputHandler.getvalidInteger();
                parkingService.createParkingLot(parkingLotSize);
            } else if (selectedOption == 1) {
                System.out.println("Please Enter Registration Number");
                String registrationNumber = CustomInputHandler.getValidString();
                System.out.println("Please Enter Color");
                String color = CustomInputHandler.getValidString();
                parkingService.parkVehicle(registrationNumber, color);
            } else if (selectedOption == 2) {
                System.out.println("Please Enter slot Number");
                Integer slotNumber = CustomInputHandler.getvalidInteger();
                parkingService.unParkVehicle(slotNumber);
            } else if (selectedOption == 3) {
                parkingService.displayAllParkedCars();
            } else if (selectedOption == 4) {
                break;
            }
        }
    }
}