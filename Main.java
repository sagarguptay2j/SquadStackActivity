import java.util.*;
import java.io.File;



class Main {
  //I have created a static variable to store my instance of ParkingLotSystem
  public static ParkingLotSystem myParkingLot;
  public static void main(String[] args) {
    try{
      File inputFile = new File("input.txt");
      Scanner sc = new Scanner(inputFile);
      while(sc.hasNextLine()){
        String inputLine = sc.nextLine();
        String[] currInput = inputLine.split(" ");
        //I am reading input line from input.txt file and ignoring the blank line and i have assumed if the method name is correct than the line have correct parameter for that method after that i am calling the respective method from my instance of ParkingLotSystem
        if(currInput.length == 0) continue;
        //If we try to execute any operation without creating parking lot
        if(!currInput[0].equals("Create_parking_lot") && myParkingLot == null){
          System.out.println("You don't have any Parking Lot yet");
          return;
        }
        switch(currInput[0]){
          case "Create_parking_lot":
            myParkingLot = new ParkingLotSystem(Integer.valueOf(currInput[1]));
            break;
          case "Park":
            myParkingLot.park(currInput[1],Integer.valueOf(currInput[3]));
            break;
          case "Slot_number_for_car_with_number":
            myParkingLot.slotNumberForCarWithVehicleNumber(currInput[1]);
            break;
          case "Leave":
            myParkingLot.release(Integer.valueOf(currInput[1]));
            break;
          case "Slot_numbers_for_driver_of_age":
            myParkingLot.slotNumbersForDriverOfAge(Integer.valueOf(currInput[1]));
            break;
          case "Vehicle_registration_number_for_driver_of_age":
            myParkingLot.vehicleNumbersForDriverOfAge(Integer.valueOf(currInput[1]));
            break;
          default:
            System.out.println("Invalid Input");
            break;   
        }
      }
      
    }
    catch(Exception e){
      return;
    }
  }
}