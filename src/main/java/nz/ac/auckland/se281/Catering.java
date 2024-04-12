package nz.ac.auckland.se281;

//import nz.ac.auckland.se281.Types.CateringType;

public class Catering extends Service {
  private Types.CateringType CateringTypeOrdered;

  public Catering(String CateringType, Integer Cost) {
    super(CateringType, Cost);
  }

  public String getCateringType() {
    return CateringTypeOrdered.toString();
  }

}
