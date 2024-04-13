package nz.ac.auckland.se281;

public class Music extends Service {

  public Music(Integer Cost) {
    super(Cost);
  }

  @Override
  public Integer getCostPerPerson() {
    return Cost;
  }

  public String getCateringType() {
    return null;
  }
  ;
}
