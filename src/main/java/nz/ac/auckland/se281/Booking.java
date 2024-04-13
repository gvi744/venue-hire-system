package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Booking {
  private String venueCode;
  private String reference;
  private String bookingDate;
  private String email;
  private int attendeeCount;
  private ArrayList<Service> serviceList = new ArrayList<>();

  public Booking(String[] options, String reference) {
    this.venueCode = options[0];
    this.bookingDate = options[1];
    this.email = options[2];
    this.attendeeCount = Integer.parseInt(options[3]);
    this.reference = reference;
  }

  public String getVenueCode() {
    return venueCode;
  }

  public String getReference() {
    return reference;
  }

  public String getBookingDate() {
    return bookingDate;
  }

  public String getEmail() {
    return email;
  }

  public int getAttendeeCount() {
    return attendeeCount;
  }

  public void addService(Service service) {
    serviceList.add(service);
  }

  public ArrayList<Service> getServices() {
    return serviceList;
  }
}
