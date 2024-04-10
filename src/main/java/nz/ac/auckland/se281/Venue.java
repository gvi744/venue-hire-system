package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Venue {

  private String venueName;
  private String venueCode;
  private int capacity;
  private int hireFee;
  private ArrayList<String> Bookings = new ArrayList<>();

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

  public Boolean checkBooking(String date) {
    return Bookings.contains(date);
  }

  public void addBooking(String date) {
    Bookings.add(date);
  }
}
