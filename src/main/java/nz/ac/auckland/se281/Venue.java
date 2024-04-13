package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Venue {

  private String venueName;
  private String venueCode;
  private int capacity;
  private int hireFee;
  private ArrayList<Booking> bookingsList = new ArrayList<>();

  public Venue(String venueName, String venueCode, int capacity, int hireFee) {
    this.venueName = venueName;
    this.venueCode = venueCode;
    this.capacity = capacity;
    this.hireFee = hireFee;
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
    for (Booking booking : bookingsList) {
      if (booking.getBookingDate().equals(date)) {
        return true;
      }
    }
    return false;
  }

  public void addBooking(String[] options, String reference) {
    Booking newBooking = new Booking(options, reference);
    bookingsList.add(newBooking);
  }

  public String nextAvailableDate(String currentDate) {

    if (currentDate.isEmpty()) {
      return "";
    }

    // String splitting to allow processing of analysis of year/month/day
    String[] dateParts = currentDate.split("/");
    Integer day = Integer.parseInt(dateParts[0]);
    Integer month = Integer.parseInt(dateParts[1]);
    Integer year = Integer.parseInt(dateParts[2]);
    String bookingDate;

    // Compare with every booking to ensure that it has not been booked before
    for (Booking booking : bookingsList) {
      bookingDate = booking.getBookingDate();

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
      } else if (bookingDay > (day + 1)) {
        return String.format("%02d/%02d/%d", day, month, year);
      } else {
        return String.format("%02d/%02d/%d", bookingDay + 1, month, year);
      }
    }

    return String.format("%02d/%02d/%d", day, month, year);
  }

  // During development, I did not realise how bookings would be accessible to VenueHireSystem so
  // this prints bookings of a specific venue directly
  public void printBookings() {
    MessageCli.PRINT_BOOKINGS_HEADER.printMessage(venueName);
    if (bookingsList.size() <= 0) {
      MessageCli.PRINT_BOOKINGS_NONE.printMessage(venueName);
    } else {
      for (Booking booking : bookingsList) {
        // Use temporary variable to store reference for printing
        String temp1 = booking.getReference();
        MessageCli.PRINT_BOOKINGS_ENTRY.printMessage(temp1, booking.getBookingDate());
      }
    }
  }

  public String[] getBookingReference(String reference) {
    String[] options = new String[5];
    for (Booking booking : bookingsList) {
      if (booking.getReference().equals(reference)) {
        // Return all the values of a booking as an array when requested through searching through
        // appropriate bookings
        options[0] = booking.getBookingDate();
        options[1] = booking.getEmail();
        options[2] = booking.getReference();
        options[3] = booking.getVenueCode();
        options[4] = Integer.toString(booking.getAttendeeCount());
      }
    }
    return options;
  }

  public Boolean checkReference(String reference) {
    for (Booking booking : bookingsList) {
      if (booking.getReference().equals(reference)) {
        return true;
      }
    }
    return false;
  }

  public ArrayList<Booking> getBookings() {
    return bookingsList;
  }
}
