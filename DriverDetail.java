import java.util.*;
// Objects of this class stores the information of driver who have parked there car
public class DriverDetail{
  String vehicleNo;
  int driverAge;
  public DriverDetail(String vehicleNo){
    this.vehicleNo = vehicleNo;
    this.driverAge = 0;
  }  
  public DriverDetail(String vehicleNo,int driverAge){
    this.vehicleNo = vehicleNo;
    this.driverAge = driverAge;
  }

  public int get_age(){
    return driverAge;
  }
  @Override
  public String toString() {
    return vehicleNo;
  }
  //Overriding hashcode and equals function to implement the hashmap by using the vehicleNo of instance of this class as key
  @Override
  public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        DriverDetail that = (DriverDetail) o;
        return vehicleNo.equals(that.vehicleNo);
    }

  @Override
  public int hashCode() {
        return vehicleNo.hashCode();
  }   
}