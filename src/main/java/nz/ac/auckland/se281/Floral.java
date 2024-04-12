package nz.ac.auckland.se281;

// import nz.ac.auckland.se281.Types.FloralType;

public class Floral extends Service {
  private Types.FloralType FloralTypeOrdered;

  public Floral(String FloralType, Integer Cost) {
    super(FloralType, Cost);
  }

  public String getCateringType() {
    return FloralTypeOrdered.toString();
  }
}
