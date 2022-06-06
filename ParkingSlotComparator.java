import java.util.*;
public class ParkingSlotComparator implements Comparator<ParkingSlot>{
              
  // Overriding compare()method of Comparator 
  // for ascending order of parking slot id          
  public int compare(ParkingSlot p1, ParkingSlot p2) {
    if (p1.id < p2.id)
      return -1;
    else if (p1.id > p2.id)
      return 1;
    return 0;
  }
}