package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Venue {

  private String venueName;
  private String venueCode;
  private int capacity;
  private int hireFee;
  private ArrayList<Booking> BookingsList = new ArrayList<>();

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
    for (Booking booking : BookingsList) {
      if (booking.getBookingDate().equals(date)) {
        return true;
      }
    }
    return false;
  }

  public void addBooking(String[] options, String reference) {
    Booking newBooking = new Booking(options, reference);
    BookingsList.add(newBooking);
  }

  public String nextAvailabeDate(String CurrentDate) {

    if (CurrentDate.isEmpty()) {
      return "";
    }

    String[] dateParts = CurrentDate.split("/");
    Integer day = Integer.parseInt(dateParts[0]);
    Integer month = Integer.parseInt(dateParts[1]);
    Integer year = Integer.parseInt(dateParts[2]);
    String bookingDate;

    for (Booking booking : BookingsList) {
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
      } else {
        return String.format("%02d/%02d/%d", bookingDay + 1, month, year);
      }
    }

    return String.format("%02d/%02d/%d", day, month, year);
  }

  public void printBookings() {
    MessageCli.PRINT_BOOKINGS_HEADER.printMessage(venueName);
    if (BookingsList.size() <= 0) {
      MessageCli.PRINT_BOOKINGS_NONE.printMessage(venueName);
    } else {
      for (Booking booking : BookingsList) {
        String temp1 = booking.getReference();
        MessageCli.PRINT_BOOKINGS_ENTRY.printMessage(temp1, booking.getBookingDate());
      }
    }
  }

  public String[] getBookingReference(String reference) {
    String[] options = new String[5];
    for (Booking booking : BookingsList) {
      if (booking.getReference().equals(reference)) {
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
    for (Booking booking : BookingsList) {
      if (booking.getReference().equals(reference)) {
        return true;
      }
    }
    return false;
  }
}
