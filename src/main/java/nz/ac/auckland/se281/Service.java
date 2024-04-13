package nz.ac.auckland.se281;

public abstract class Service {
  protected String cateringType = "";
  protected Integer cost;

  public Service(String cateringType, Integer cost) {
    this.cateringType = cateringType;
    this.cost = cost;
  }

  public Service(Integer cost) {
    this.cost = cost;
  }

  public abstract Integer getCostPerPerson();

  public abstract String getCateringType();
}
