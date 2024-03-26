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
    MessageCli.NO_VENUES.printMessage();
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    this.venueName = venueName;
    this.venueCode = venueCode;
    this.capacity = Integer.parseInt(capacityInput);
    int hireFee;

    if (venueName.isEmpty()) {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
    } else if (codeList.contains(venueCode)) {
      MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, venueName);
    } else if (this.capacity < 0) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " positive");
    } else {
      if (!isInteger(hireFeeInput)) {
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", "");
        return;
      }
      if (Integer.parseInt(hireFeeInput) == 0) {
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", " positive");
        return;
      }
      codeList.add(venueCode);
      hireFee = Integer.parseInt(hireFeeInput);
      MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(this.venueName, this.venueCode);
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
