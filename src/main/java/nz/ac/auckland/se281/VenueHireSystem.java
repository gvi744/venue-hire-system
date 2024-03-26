package nz.ac.auckland.se281;

// Manually added arrayList functionality
import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {

  // Declare instance variables
  private String venueName;
  private String venueCode;
  private int capacity;
  private int hireFee;
  private ArrayList<String> codeList = new ArrayList<String>();

  public VenueHireSystem() {}

  public void printVenues() {
    System.out.println("There are no venues in the system. Please create a venue first.");
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    this.venueName = venueName;
    this.venueCode = venueCode;
    this.capacity = Integer.parseInt(capacityInput);
    int hireFee;

    if (venueName.isEmpty()) {
      System.out.println("Venue not created: venue name must not be empty.");
    } else if (codeList.contains(venueCode)) {
      System.out.println(
          "Venue not created: code '" + venueCode + "' is already used for '" + venueName + "'.");
    } else if (this.capacity < 0) {
      System.out.println("Venue not created: capacity must be a positive number.");
    } else {
      if (!isInteger(hireFeeInput)) {
        System.out.println("Venue not created: hire fee must be a number.");
        return;
      }
      if (Integer.parseInt(hireFeeInput) == 0) {
        System.out.println("Venue not created: hire fee must be a positive number.");
        return;
      }
      codeList.add(venueCode);
      hireFee = Integer.parseInt(hireFeeInput);
      System.out.println(
          "Successfully created venue '" + this.venueName + "' (" + this.venueCode + ").");
    }
  }

  public boolean isInteger(String input) {
    try {
      Integer.parseInt(input);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  public void setSystemDate(String dateInput) {
    // TODO implement this method
  }

  public void printSystemDate() {
    // TODO implement this method
  }

  public void makeBooking(String[] options) {
    // TODO implement this method
  }

  public void printBookings(String venueCode) {
    // TODO implement this method
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    // TODO implement this method
  }

  public void addServiceMusic(String bookingReference) {
    // TODO implement this method
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    // TODO implement this method
  }

  public void viewInvoice(String bookingReference) {
    // TODO implement this method
  }
}
