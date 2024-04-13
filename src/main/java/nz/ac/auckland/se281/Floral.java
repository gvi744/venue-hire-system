package nz.ac.auckland.se281;

public class Floral extends Service {
  // private Types.FloralType FloralTypeOrdered;
  private String floralTypeOrdered;

  public Floral(String floralType, Integer cost) {
    super(floralType, cost);
    this.floralTypeOrdered = floralType;
  }

  @Override
  public String getCateringType() {
    return floralTypeOrdered.toString();
  }

  @Override
  public Integer getCostPerPerson() {
    return cost;
  }
}
