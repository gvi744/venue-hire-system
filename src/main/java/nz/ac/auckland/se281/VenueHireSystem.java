package nz.ac.auckland.se281;

// Manually added arrayList functionality
import java.util.ArrayList;
import nz.ac.auckland.se281.Types.CateringType;
import nz.ac.auckland.se281.Types.FloralType;

public class VenueHireSystem {

  // Declaration of instance variables
  private ArrayList<String> codeList = new ArrayList<String>();
  private ArrayList<Venue> venueList = new ArrayList<>();
  private ArrayList<String> referenceList = new ArrayList<String>();
  private String CurrentDate = "";

  private String Catering;
  private Boolean CateringOrder = false;
  private Integer CateringCost = 0;

  private Boolean Music = false;
  private Integer MusicCost = 0;

  private String Floral;
  private Boolean FloralOrder = false;
  private Integer FloralCost = 0;

  public VenueHireSystem() {}

  public void printVenues() {
    if (codeList.size() <= 0) {
      MessageCli.NO_VENUES.printMessage();
    }

    String nextAvailableDate = "";

    if (venueList.size() == 1) {
      // Grab the values for the one venue instance
      MessageCli.NUMBER_VENUES.printMessage("is", "one", "");
      Venue venue = venueList.get(0);
      nextAvailableDate = venue.nextAvailabeDate(CurrentDate);
      MessageCli.VENUE_ENTRY.printMessage(
          venue.getName(),
          venue.getCode(),
          Integer.toString(venue.getCapacity()),
          Integer.toString(venue.getHireFee()),
          nextAvailableDate);
    } else if (codeList.size() > 1 && codeList.size() < 10) {
      MessageCli.NUMBER_VENUES.printMessage("are", NumberToWord(codeList.size()), "s");
    } else {
      MessageCli.NUMBER_VENUES.printMessage("are", Integer.toString(codeList.size()), "s");
    }
    // Assuming that the venue list is greater than one, print all instances of venues
    for (Venue venue : venueList) {
      nextAvailableDate = venue.nextAvailabeDate(CurrentDate);
      MessageCli.VENUE_ENTRY.printMessage(
          venue.getName(),
          venue.getCode(),
          Integer.toString(venue.getCapacity()),
          Integer.toString(venue.getHireFee()),
          nextAvailableDate);
    }
  }

  public void createVenue(
      String venueName, String venueCode, String capacityInput, String hireFeeInput) {
    // Custom handling for input parameters per specification
    if (venueName.isEmpty()) {
      MessageCli.VENUE_NOT_CREATED_EMPTY_NAME.printMessage();
    } else if (codeList.contains(venueCode)) {
      // Check if venue code already exists in the list and if so go through venue list and get
      // appropriate name
      for (Venue venue : venueList) {
        if (venue.getCode().equals(venueCode)) {
          venueName = venue.getName();
        }
      }
      MessageCli.VENUE_NOT_CREATED_CODE_EXISTS.printMessage(venueCode, venueName);
    } else if (!isInteger(capacityInput)) {
      // Using custom class defined underneath, check if capacity is able to be converted to an
      // integer
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", "");
      return;
    } else if (Integer.parseInt(capacityInput) < 0) {
      MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("capacity", " positive");
    } else {
      if (!isInteger(hireFeeInput)) {
        // Using custom class defined underneath, check if hire fee is able to be converted to an
        // integer
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", "");
        return;
      }
      if (Integer.parseInt(hireFeeInput) <= 0) {
        MessageCli.VENUE_NOT_CREATED_INVALID_NUMBER.printMessage("hire fee", " positive");
        return;
      }
      // Create venue where appropriate assuming all input parameters are of desired quality
      codeList.add(venueCode);
      Venue newVenue =
          new Venue(
              venueName,
              venueCode,
              Integer.parseInt(capacityInput),
              Integer.parseInt(hireFeeInput));
      venueList.add(newVenue);
      MessageCli.VENUE_SUCCESSFULLY_CREATED.printMessage(venueName, venueCode);
    }
  }

  // Custom class to check if the string input for hire fee is an integer
  public boolean isInteger(String input) {
    try {
      // Attempt to parse and handle exception if it occurs
      // Using NumberFormatException instead of Exception to cover specific case
      Integer.parseInt(input);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  // Custom class to covert number to word when printing number of venues less than ten
  public String NumberToWord(int number) {
    String[] words = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    if (number > 1 && number <= 9) {
      // Only returning numbers 2 - 9 because 1 has its separate exception
      return words[number - 1];
    } else {
      // Semi-unnecessary exception handling otherwise compiler doesn't like it
      throw new IllegalArgumentException("Number must be between 2 and 9 (inclusive).");
    }
  }

  public void setSystemDate(String dateInput) {
    CurrentDate = dateInput;
    MessageCli.DATE_SET.printMessage(dateInput);
  }

  public void printSystemDate() {
    if (CurrentDate.isEmpty()) {
      MessageCli.CURRENT_DATE.printMessage("not set");
    } else {
      MessageCli.CURRENT_DATE.printMessage(CurrentDate);
    }
  }

  public void makeBooking(String[] options) {
    if (CurrentDate.isEmpty()) {
      MessageCli.BOOKING_NOT_MADE_DATE_NOT_SET.printMessage();
    } else if (venueList.size() == 0) {
      MessageCli.BOOKING_NOT_MADE_NO_VENUES.printMessage();
    } else {

      String venueName = null;
      Integer venueCapacity = 0;
      Boolean venueFound = false;

      for (Venue venue : venueList) {
        if (venue.getCode().contains(options[0])) {
          venueFound = true;
        }
        if (venue.getCode().equals(options[0])) {
          venueName = venue.getName();
          venueCapacity = venue.getCapacity();
        }
        // Checking if already venue is already booked
        if (venue.checkBooking(options[1])) {
          MessageCli.BOOKING_NOT_MADE_VENUE_ALREADY_BOOKED.printMessage(venueName, options[1]);
          return;
        }
      }

      if (!venueFound) {
        MessageCli.BOOKING_NOT_MADE_VENUE_NOT_FOUND.printMessage(options[0]);
        return;
      }

      String[] dateParts = options[1].split("/");
      String[] currentDateParts = CurrentDate.split("/");

      Integer bookingDay = Integer.parseInt(dateParts[0]);
      Integer bookingMonth = Integer.parseInt(dateParts[1]);
      Integer bookingYear = Integer.parseInt(dateParts[2]);
      Integer Day = Integer.parseInt(currentDateParts[0]);
      Integer Month = Integer.parseInt(currentDateParts[1]);
      Integer Year = Integer.parseInt(currentDateParts[2]);

      if (bookingYear < Year) {
        MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(options[1], CurrentDate);
        return;
      } else if (bookingMonth < Month) {
        MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(options[1], CurrentDate);
        return;
      } else if (bookingDay < Day) {
        MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(options[1], CurrentDate);
        return;
      }

      // Adjusting number of attendees
      if (Integer.parseInt(options[3]) < (venueCapacity / 4)) {
        MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
            options[3], Integer.toString((venueCapacity / 4)), Integer.toString(venueCapacity));
        options[3] = Integer.toString(venueCapacity / 4);
      } else if (Integer.parseInt(options[3]) > venueCapacity) {
        MessageCli.BOOKING_ATTENDEES_ADJUSTED.printMessage(
            options[3], Integer.toString(venueCapacity), Integer.toString(venueCapacity));
        options[3] = Integer.toString(venueCapacity);
      }

      // Seperate String to only generate Reference once per new Booking
      String Reference = BookingReferenceGenerator.generateBookingReference();
      referenceList.add(Reference);
      // Make sure to tell venue that it has been booked on that date
      for (Venue venue : venueList) {
        if (venue.getName().equals(venueName)) {
          venue.addBooking(options, Reference);
        }
      }
      MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(Reference, venueName, options[1], options[3]);
    }
  }

  public void printBookings(String venueCode) {
    if (!codeList.contains(venueCode)) {
      MessageCli.PRINT_BOOKINGS_VENUE_NOT_FOUND.printMessage(venueCode);
    } else {
      for (Venue venue : venueList) {
        if (venue.getCode().equals(venueCode)) {
          venue.printBookings();
        }
      }
    }
  }

  public void addCateringService(String bookingReference, CateringType cateringType) {
    if (referenceList.contains(bookingReference)) {
      Catering = cateringType.getName();
      CateringCost = cateringType.getCostPerPerson();
      CateringOrder = true;
      MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(
          "Catering (" + cateringType.getName() + ")", bookingReference);
    } else {
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Catering", bookingReference);
    }
  }

  public void addServiceMusic(String bookingReference) {
    if (referenceList.contains(bookingReference)) {
      Music = true;
      MusicCost = 500;
      MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Music", bookingReference);
    } else {
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Music", bookingReference);
    }
  }

  public void addServiceFloral(String bookingReference, FloralType floralType) {
    if (referenceList.contains(bookingReference)) {
      Floral = floralType.getName();
      FloralOrder = true;
      FloralCost = floralType.getCost();
      MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(
          "Floral (" + floralType.getName() + ")", bookingReference);
    } else {
      MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Floral", bookingReference);
    }
  }

  public void viewInvoice(String bookingReference) {
    String options[] = new String[5];

    if (referenceList.contains(bookingReference)) {

      for (Venue venue : venueList) {
        if (venue.checkReference(bookingReference)) {
          options = venue.getBookingReference(bookingReference);

          MessageCli.INVOICE_CONTENT_TOP_HALF.printMessage(
              bookingReference, options[1], CurrentDate, options[0], options[4], venue.getName());

          MessageCli.INVOICE_CONTENT_VENUE_FEE.printMessage(Integer.toString(venue.getHireFee()));

          if (CateringOrder) {
            MessageCli.INVOICE_CONTENT_CATERING_ENTRY.printMessage(
                Catering, Integer.toString(CateringCost * Integer.parseInt(options[4])));
          }
          if (Music) {
            MessageCli.INVOICE_CONTENT_MUSIC_ENTRY.printMessage("500");
          }
          if (FloralOrder) {
            MessageCli.INVOICE_CONTENT_FLORAL_ENTRY.printMessage(
                Floral, Integer.toString(FloralCost));
          }

          String totalCost =
              Integer.toString(
                  venue.getHireFee()
                      + (CateringCost * Integer.parseInt(options[4]))
                      + MusicCost
                      + FloralCost);
          MessageCli.INVOICE_CONTENT_BOTTOM_HALF.printMessage(totalCost);
        }
      }

    } else {
      MessageCli.VIEW_INVOICE_BOOKING_NOT_FOUND.printMessage(bookingReference);
    }
  }
}
