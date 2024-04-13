package nz.ac.auckland.se281;

public class Floral extends Service {
  // private Types.FloralType FloralTypeOrdered;
  private String FloralTypeOrdered;

  public Floral(String FloralType, Integer Cost) {
    super(FloralType, Cost);
    this.FloralTypeOrdered = FloralType;
  }

  @Override
  public String getCateringType() {
    return FloralTypeOrdered.toString();
  }

  @Override
  public Integer getCostPerPerson() {
    return Cost;
  }
}
