import java.util.*;

public class ParkingLotSystem{
  //I have used priorityqueue to store the available parking slot to get the nearest slot available from the entrance for the driver
  PriorityQueue<ParkingSlot> availableParkingSlot;
  int totalParkingSlot;
  //I have used 3 hashmaps,first to keep the mapping of parking slot with the driver it has been assigned and the second and thrid map are used to optimize the operations on Parking Lot
  Map<ParkingSlot,DriverDetail> mappedByParkingSlot;
  Map<DriverDetail,ParkingSlot> mappedByDriverDetail;
  Map<Integer,Set<DriverDetail>> vehiclesByAge;
  //This constructor takes the number of slots as argument and create an instance of ParkingLotSystem
  ParkingLotSystem(int totalParkingSlot){
    this.totalParkingSlot = totalParkingSlot;
    availableParkingSlot = new PriorityQueue<>(new ParkingSlotComparator());
    mappedByParkingSlot = new HashMap<>();
    mappedByDriverDetail = new HashMap<>();
    vehiclesByAge = new HashMap<>();
    //creating the parking slot object and adding it in availableParkingSlot queue
    for(int i = 0;i < totalParkingSlot;i++){
      availableParkingSlot.offer(new ParkingSlot(i+1));
    }
    System.out.println("Created parking of "+totalParkingSlot+" slots");
  }
//Implemented the command Park
  public void park(String vehicleNo,int age){
    if(mappedByDriverDetail.containsKey(new DriverDetail(vehicleNo))){
      System.out.println("Car having same Vehicle Number is already parked");
      return;
    }
    if(availableParkingSlot.isEmpty()){
      System.out.println("No Parking Slot Available,Sorry For Inconvenience");
      return;
    }
    ParkingSlot assignedSlot = availableParkingSlot.poll();
    DriverDetail newDriver = new DriverDetail(vehicleNo,age);
    assignedSlot.reserveSlot();
    mappedByDriverDetail.put(newDriver,assignedSlot);
    mappedByParkingSlot.put(assignedSlot,newDriver);
    vehiclesByAge.computeIfAbsent(age,k -> new HashSet<>()).add(newDriver);
    System.out.println("Car with vehicle registration number \""+vehicleNo+
                       "\" has been parked at slot number "+assignedSlot.get_id());    
  }

  //Implemented the command Slot_numbers_for_driver_of_age
  public void slotNumbersForDriverOfAge(int age){
    int count = vehiclesByAge.getOrDefault(age,new HashSet<>()).size();
    for(DriverDetail driverOfThisAge : vehiclesByAge.getOrDefault(age,new HashSet<>())){
      System.out.print(mappedByDriverDetail.get(driverOfThisAge).get_id());
      if(--count > 0)
        System.out.print(",");
    }
    System.out.println();
  }

  //Implemented the command Slot_number_for_car_with_number
  public void slotNumberForCarWithVehicleNumber(String vehicleNo){
    if(!mappedByDriverDetail.containsKey(new DriverDetail(vehicleNo))){
      System.out.println("Car with the given vehicle registration number is not present in Parking Lot");
      return;
    }
    System.out.println(mappedByDriverDetail.get(new DriverDetail(vehicleNo)).get_id());
  }

  //Implemented the command Vehicle_registration_number_for_driver_of_age
  public void vehicleNumbersForDriverOfAge(int age){
    int count = vehiclesByAge.getOrDefault(age,new HashSet<>()).size();
    for(DriverDetail driverOfThisAge : vehiclesByAge.getOrDefault(age,new HashSet<>())){
      System.out.print("\""+driverOfThisAge+"\"");
      if(--count > 0)
        System.out.print(",");
    }
    System.out.println();    
  }

  //implemented the command Leave
  public void release(int id){
    if(!mappedByParkingSlot.containsKey(new ParkingSlot(id))){
      System.out.println("No car is parked on this slot");
      return;
    }
    DriverDetail currDriver = mappedByParkingSlot.get(new ParkingSlot(id));
    mappedByParkingSlot.remove(new ParkingSlot(id));
    vehiclesByAge.get(currDriver.get_age()).remove(currDriver);
    mappedByDriverDetail.get(currDriver).releaseSlot();
    availableParkingSlot.offer(mappedByDriverDetail.get(currDriver));
    mappedByDriverDetail.remove(currDriver);
    System.out.println("Slot number "+id+" vacated, the car with vehicle registration number \""
                       +currDriver+"\" left the space, the driver of the car was of age "+currDriver.get_age());
  }
  
}

