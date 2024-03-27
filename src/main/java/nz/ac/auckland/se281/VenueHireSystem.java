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
  private ArrayList<Venue> venueList = new ArrayList<>();

  public VenueHireSystem() {}

  public void printVenues() {
    if (codeList.size() == 0) {
      MessageCli.NO_VENUES.printMessage();
    } else if (codeList.size() == 1) {
      MessageCli.NUMBER_VENUES.printMessage("is", "one", "");
      MessageCli.VENUE_ENTRY.printMessage(
          venueName, venueCode, Integer.toString(capacity), Integer.toString(hireFee));
    } else if (codeList.size() > 1 && codeList.size() < 10) {
      MessageCli.NUMBER_VENUES.printMessage("are", NumberToWord(codeList.size()), "s");
      for (int i = 0; i < codeList.size(); i++) {
        MessageCli.VENUE_ENTRY.printMessage(
            venueName, venueCode, Integer.toString(capacity), Integer.toString(hireFee));
      }
    } else {
      MessageCli.NUMBER_VENUES.printMessage("are", Integer.toString(codeList.size()), "s");
      for (String venueCode : codeList) {
        MessageCli.VENUE_ENTRY.printMessage(
            this.venueName,
            this.venueCode,
            Integer.toString(this.capacity),
            Integer.toString(this.hireFee));
      }
    }
  }

  public class Venue {

    private String venueName;
    private String venueCode;
    private int capacity;
    private int hireFee;
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    Venue Venue = new Venue();
    Venue.venueName = venueName;
    Venue.venueCode = venueCode;

    if (venueName.isEmpty()) {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
    } else if (codeList.contains(venueCode)) {
      MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, venueName);
    } else if (Integer.parseInt(capacityInput) < 0) {
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
      Venue.capacity = Integer.parseInt(capacityInput);
      Venue.hireFee = Integer.parseInt(hireFeeInput);
      MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
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

  public String NumberToWord(int number) {
    String[] words = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    if (number > 1 && number <= 9) {
      return words[number - 1];
    } else {
      throw new IllegalArgumentException("Number must be between 2 and 9 (inclusive).");
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
