package nz.ac.auckland.se281;

public class Catering extends Service {
  private String CateringTypeOrdered;

  public Catering(String CateringType, Integer Cost) {
    super(CateringType, Cost);
    this.CateringTypeOrdered = CateringType;
  }

  @Override
  public String getCateringType() {
    return CateringTypeOrdered.toString();
  }

  @Override
  public Integer getCostPerPerson() {
    return cost;
  }
}
