package nz.ac.auckland.se281;

public class Catering extends Service {
  private String cateringTypeOrdered;

  public Catering(String cateringType, Integer cost) {
    super(cateringType, cost);
    this.cateringTypeOrdered = cateringType;
  }

  @Override
  public String getCateringType() {
    return cateringTypeOrdered.toString();
  }

  @Override
  public Integer getCostPerPerson() {
    return cost;
  }
}
