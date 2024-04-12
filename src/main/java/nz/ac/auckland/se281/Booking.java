package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Booking {
  private String VenueCode;
  private String Reference;
  private String BookingDate;
  private String Email;
  private int attendeeCount;
  private ArrayList<Service> serviceList = new ArrayList<>();

  public Booking(String[] options, String reference) {
    this.VenueCode = options[0];
    this.BookingDate = options[1];
    this.Email = options[2];
    this.attendeeCount = Integer.parseInt(options[3]);
    this.Reference = reference;
  }

  public String getVenueCode() {
    return VenueCode;
  }

  public String getReference() {
    return Reference;
  }

  public String getBookingDate() {
    return BookingDate;
  }

  public String getEmail() {
    return Email;
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
