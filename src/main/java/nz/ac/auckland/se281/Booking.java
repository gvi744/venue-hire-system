package nz.ac.auckland.se281;

public class Booking {
  private String VenueCode;
  private String Reference;
  private String BookingDate;
  private String Email;
  private int attendeeCount;

  public Booking(String[] options) {
    this.VenueCode = options[0];
    this.BookingDate = options[1];
    this.Email = options[2];
    this.attendeeCount = Integer.parseInt(options[3]);
  }
}
