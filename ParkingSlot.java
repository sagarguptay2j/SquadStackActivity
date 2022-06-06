import java.util.*;

//This represents the parking slot it has two parameters slot id and reserved status
public class ParkingSlot{
  int id;
  boolean is_reserve;
  public ParkingSlot(int id){
    this.id = id;
    this.is_reserve = false;
  }  
  public int get_id(){
    return id;
  }
  public void reserveSlot(){
    is_reserve = true;
  }
  public void releaseSlot(){
    is_reserve = false;
  }
  public boolean isreserved(){
    return is_reserve;
  }
  @Override
  public String toString() {
    return String.valueOf(id);
  }
  //Overriding hashcode and equals function to implement the hashmap by using the id of instance of this class as key
  @Override
  public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ParkingSlot that = (ParkingSlot) o;
        return id == that.id;
    }

  @Override
  public int hashCode() {
        return this.id;
  } 
}