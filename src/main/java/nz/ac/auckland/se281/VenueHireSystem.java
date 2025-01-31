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
  private String currentDate = "";

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
      nextAvailableDate = venue.nextAvailableDate(currentDate);
      MessageCli.VENUE_ENTRY.printMessage(
          venue.getName(),
          venue.getCode(),
          Integer.toString(venue.getCapacity()),
          Integer.toString(venue.getHireFee()),
          nextAvailableDate);
    } else if (codeList.size() > 1 && codeList.size() < 10) {
      MessageCli.NUMBER_VENUES.printMessage("are", numberToWord(codeList.size()), "s");
    } else {
      MessageCli.NUMBER_VENUES.printMessage("are", Integer.toString(codeList.size()), "s");
    }
    // Assuming that the venue list is greater than one, print all instances of venues
    for (Venue venue : venueList) {
      nextAvailableDate = venue.nextAvailableDate(currentDate);
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
  public String numberToWord(int number) {
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
    currentDate = dateInput;
    MessageCli.DATE_SET.printMessage(dateInput);
  }

  public void printSystemDate() {
    if (currentDate.isEmpty()) {
      MessageCli.CURRENT_DATE.printMessage("not set");
    } else {
      MessageCli.CURRENT_DATE.printMessage(currentDate);
    }
  }

  public void makeBooking(String[] options) {
    if (currentDate.isEmpty()) {
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
      String[] currentDateParts = currentDate.split("/");

      Integer bookingDay = Integer.parseInt(dateParts[0]);
      Integer bookingMonth = Integer.parseInt(dateParts[1]);
      Integer bookingYear = Integer.parseInt(dateParts[2]);
      Integer day = Integer.parseInt(currentDateParts[0]);
      Integer month = Integer.parseInt(currentDateParts[1]);
      Integer year = Integer.parseInt(currentDateParts[2]);

      if (bookingYear < year) {
        MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(options[1], currentDate);
        return;
      } else if (bookingMonth < month) {
        MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(options[1], currentDate);
        return;
      } else if (bookingDay < day) {
        MessageCli.BOOKING_NOT_MADE_PAST_DATE.printMessage(options[1], currentDate);
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
      String reference = BookingReferenceGenerator.generateBookingReference();
      referenceList.add(reference);
      // Make sure to tell venue that it has been booked on that date
      for (Venue venue : venueList) {
        if (venue.getName().equals(venueName)) {
          venue.addBooking(options, reference);
        }
      }
      MessageCli.MAKE_BOOKING_SUCCESSFUL.printMessage(reference, venueName, options[1], options[3]);
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

  // Adding catering by iterating until proper booking from proper venue
  public void addCateringService(String bookingReference, CateringType cateringType) {
    for (Venue venue : venueList) {
      if (venue.checkReference(bookingReference)) {
        Catering cateringService =
            new Catering(cateringType.getName(), cateringType.getCostPerPerson());
        ArrayList<Booking> retrievedBookings = venue.getBookings();
        for (Booking booking : retrievedBookings) {
          if (booking.getReference().equals(bookingReference)) {
            booking.addService(cateringService);
            MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(
                "Catering (" + cateringType.getName() + ")", bookingReference);
            return;
            // Return to stop iterating through improper venues / references
          }
        }
      }
    }
    MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Catering", bookingReference);
  }

  // Adding music by iterating until proper booking is reached from proper venue
  public void addServiceMusic(String bookingReference) {
    for (Venue venue : venueList) {
      if (venue.checkReference(bookingReference)) {
        // Music cost is constant at 500 regardless of attendees or type of music
        Music musicService = new Music(500);
        ArrayList<Booking> retrivedBookings = venue.getBookings();
        for (Booking booking : retrivedBookings) {
          if (booking.getReference().equals(bookingReference)) {
            booking.addService(musicService);
            MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage("Music", bookingReference);
            return;
          }
        }
      }
    }
    MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Music", bookingReference);
  }

  // Adding Floral Services by iterating through every booking in every venue
  public void addServiceFloral(String bookingReference, FloralType floralType) {
    for (Venue venue : venueList) {
      if (venue.checkReference(bookingReference)) {
        // Floral cost is dependent on type of floral, not number of attendees
        Floral floralService = new Floral(floralType.getName(), floralType.getCost());
        ArrayList<Booking> retrievedBookings = venue.getBookings();
        for (Booking booking : retrievedBookings) {
          if (booking.getReference().equals(bookingReference)) {
            booking.addService(floralService);
            MessageCli.ADD_SERVICE_SUCCESSFUL.printMessage(
                "Floral (" + floralType.getName() + ")", bookingReference);
            return;
          }
        }
      }
    }
    MessageCli.SERVICE_NOT_ADDED_BOOKING_NOT_FOUND.printMessage("Floral", bookingReference);
  }

  public void viewInvoice(String bookingReference) {
    // Initialising placeholder array for booking details
    String options[] = new String[5];

    if (referenceList.contains(bookingReference)) {

      for (Venue venue : venueList) {
        if (venue.checkReference(bookingReference)) {
          // Initaliste totalCost variable and get details from specific booking
          options = venue.getBookingReference(bookingReference);
          Integer totalCost = venue.getHireFee();

          MessageCli.INVOICE_CONTENT_TOP_HALF.printMessage(
              bookingReference, options[1], currentDate, options[0], options[4], venue.getName());

          MessageCli.INVOICE_CONTENT_VENUE_FEE.printMessage(Integer.toString(venue.getHireFee()));

          // Get booking list to find specific booking details
          ArrayList<Booking> retrievedBookings = venue.getBookings();
          for (Booking booking : retrievedBookings) {

            if (booking.getReference().equals(bookingReference)) {

              ArrayList<Service> serviceList = booking.getServices();

              for (Service service : serviceList) {
                // Initialise temp variables so code does not become cluttered
                String costPerPerson = "";
                String cateringTypeOrdered = "";
                Integer costOfCatering = 0;
                String floralTypeOrdered = "";

                if (service instanceof Catering) {
                  cateringTypeOrdered = service.getCateringType();
                  // Multiply cost per person by number of attendees to get total catering fee
                  costOfCatering = service.getCostPerPerson() * Integer.parseInt(options[4]);
                  totalCost += costOfCatering;
                  MessageCli.INVOICE_CONTENT_CATERING_ENTRY.printMessage(
                      cateringTypeOrdered, Integer.toString(costOfCatering));
                }

                if (service instanceof Music) {
                  costPerPerson = Integer.toString(service.getCostPerPerson());
                  totalCost += service.getCostPerPerson();
                  MessageCli.INVOICE_CONTENT_MUSIC_ENTRY.printMessage(costPerPerson);
                }

                if (service instanceof Floral) {
                  floralTypeOrdered = service.getCateringType();
                  costPerPerson = Integer.toString(service.getCostPerPerson());
                  totalCost += service.getCostPerPerson();
                  MessageCli.INVOICE_CONTENT_FLORAL_ENTRY.printMessage(
                      floralTypeOrdered, costPerPerson);
                }
              }
            }
          }

          MessageCli.INVOICE_CONTENT_BOTTOM_HALF.printMessage(Integer.toString(totalCost));
        }
      }

    } else {
      MessageCli.VIEW_INVOICE_BOOKING_NOT_FOUND.printMessage(bookingReference);
    }
  }
}
