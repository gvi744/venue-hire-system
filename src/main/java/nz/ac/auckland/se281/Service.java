package nz.ac.auckland.se281;

public abstract class Service {
  protected String CateringType = "";
  protected Integer Cost;

  public Service(String CateringType, Integer Cost) {
    this.CateringType = CateringType;
    this.Cost = Cost;
  }

  public Service(Integer Cost) {
    this.Cost = Cost;
  }

  public abstract Integer getCostPerPerson();

  public abstract String getCateringType();
}
