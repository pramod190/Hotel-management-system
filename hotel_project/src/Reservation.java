public class Reservation {
    private int reservationId;
    private Guest guest;
    private Room room;
    private int nights;
    private double totalAmount;
    private String status; // BOOKED, CHECKED_OUT

    public Reservation(int reservationId, Guest guest, Room room, int nights) {
        this.reservationId = reservationId;
        this.guest = guest;
        this.room = room;
        this.nights = nights;
        this.totalAmount = nights * room.getPricePerNight();
        this.status = "BOOKED";
    }

    public int getReservationId() { return reservationId; }
    public Guest getGuest() { return guest; }
    public Room getRoom() { return room; }
    public int getNights() { return nights; }
    public double getTotalAmount() { return totalAmount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Reservation #" + reservationId + " | " + guest.getName() + " -> Room "
                + room.getRoomNumber() + " (" + room.getType() + ") | " + nights + " night(s) | Total: Rs."
                + totalAmount + " | Status: " + status;
    }
}
