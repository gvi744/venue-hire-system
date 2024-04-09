package nz.ac.auckland.se281;

public class Venue {

    private String venueName;
    private String venueCode;
    private int capacity;
    private int hireFee;

    public Venue(String VenueName, String VenueCode, int Capacity, int HireFee) {
      this.venueName = VenueName;
      this.venueCode = VenueCode;
      this.capacity = Capacity;
      this.hireFee = HireFee;
    }

    // Getters for the venue class
    public String getName() {
      return venueName;
    }

    public String getCode() {
      return venueCode;
    }

    public int getCapacity() {
      return capacity;
    }

    public int getHireFee() {
      return hireFee;
    }

  }