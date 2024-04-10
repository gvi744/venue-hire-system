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

  public String nextAvailabeDate(String CurrentDate) {

    if (CurrentDate.isEmpty()) {
      return "";
    }

    String[] dateParts = CurrentDate.split("/");
    Integer day = Integer.parseInt(dateParts[0]);
    Integer month = Integer.parseInt(dateParts[1]);
    Integer year = Integer.parseInt(dateParts[2]);

    for (String bookingDate : Bookings) {
      String[] bookingParts = bookingDate.split("/");
      Integer bookingDay = Integer.parseInt(bookingParts[0]);
      Integer bookingMonth = Integer.parseInt(bookingParts[1]);
      Integer bookingYear = Integer.parseInt(bookingParts[2]);

      if (bookingYear < year) {
        continue;
      } else if (bookingMonth < month) {
        continue;
      } else if (bookingDay < day) {
        continue;
      } else if (bookingDay == day) {
        day++;
      } else {
        return String.format("%02d/%02d/%d", bookingDay + 1, month, year);
      }
    }

    return String.format("%02d/%02d/%d", day, month, year);
  }
}
