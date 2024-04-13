package nz.ac.auckland.se281;

public class Music extends Service {

  public Music(Integer cost) {
    super(cost);
  }

  @Override
  public Integer getCostPerPerson() {
    return cost;
  }

  @Override
  public String getCateringType() {
    return null;
  }
}
