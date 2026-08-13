import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private String hotelName;
    private List<Room> rooms;
    private List<Guest> guests;
    private List<Reservation> reservations;

    private int nextGuestId = 1;
    private int nextReservationId = 1;

    public Hotel(String hotelName) {
        this.hotelName = hotelName;
        this.rooms = new ArrayList<>();
        this.guests = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    // ---------- Room management ----------
    public void addRoom(Room room) {
        rooms.add(room);
    }

    public List<Room> getAllRooms() {
        return rooms;
    }

    public List<Room> getAvailableRooms() {
        List<Room> available = new ArrayList<>();
        for (Room r : rooms) {
            if (r.isAvailable()) available.add(r);
        }
        return available;
    }

    private Room findRoomByNumber(int roomNumber) {
        for (Room r : rooms) {
            if (r.getRoomNumber() == roomNumber) return r;
        }
        return null;
    }

    // ---------- Guest management ----------
    public Guest registerGuest(String name, String phone, String email) {
        Guest guest = new Guest(nextGuestId++, name, phone, email);
        guests.add(guest);
        return guest;
    }

    public List<Guest> getAllGuests() {
        return guests;
    }

    // ---------- Reservation / booking ----------
    public Reservation bookRoom(Guest guest, int roomNumber, int nights) {
        Room room = findRoomByNumber(roomNumber);
        if (room == null) {
            System.out.println("Room " + roomNumber + " does not exist.");
            return null;
        }
        if (!room.isAvailable()) {
            System.out.println("Room " + roomNumber + " is not available.");
            return null;
        }
        room.setAvailable(false);
        Reservation reservation = new Reservation(nextReservationId++, guest, room, nights);
        reservations.add(reservation);
        return reservation;
    }

    public boolean checkout(int reservationId) {
        for (Reservation res : reservations) {
            if (res.getReservationId() == reservationId && res.getStatus().equals("BOOKED")) {
                res.setStatus("CHECKED_OUT");
                res.getRoom().setAvailable(true);
                return true;
            }
        }
        return false;
    }

    public List<Reservation> getAllReservations() {
        return reservations;
    }

    public Reservation findReservation(int reservationId) {
        for (Reservation res : reservations) {
            if (res.getReservationId() == reservationId) return res;
        }
        return null;
    }

    public String getHotelName() {
        return hotelName;
    }
}
