import java.util.List;
import java.util.Scanner;

public class Main {
    static Hotel hotel = new Hotel("Sunrise Grand Hotel");
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        seedData();

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1: viewAvailableRooms(); break;
                case 2: viewAllRooms(); break;
                case 3: registerGuest(); break;
                case 4: bookRoom(); break;
                case 5: checkout(); break;
                case 6: viewAllReservations(); break;
                case 7: viewAllGuests(); break;
                case 0:
                    running = false;
                    System.out.println("Thank you for using " + hotel.getHotelName() + ". Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
        sc.close();
    }

    private static void seedData() {
        hotel.addRoom(new Room(101, "Single", 1500));
        hotel.addRoom(new Room(102, "Single", 1500));
        hotel.addRoom(new Room(201, "Double", 2500));
        hotel.addRoom(new Room(202, "Double", 2500));
        hotel.addRoom(new Room(301, "Suite", 5000));
    }

    private static void printMenu() {
        System.out.println("\n===== " + hotel.getHotelName() + " - Management System =====");
        System.out.println("1. View Available Rooms");
        System.out.println("2. View All Rooms");
        System.out.println("3. Register New Guest");
        System.out.println("4. Book a Room");
        System.out.println("5. Checkout (End Reservation)");
        System.out.println("6. View All Reservations");
        System.out.println("7. View All Guests");
        System.out.println("0. Exit");
    }

    private static void viewAvailableRooms() {
        List<Room> rooms = hotel.getAvailableRooms();
        if (rooms.isEmpty()) {
            System.out.println("No rooms currently available.");
            return;
        }
        System.out.println("--- Available Rooms ---");
        for (Room r : rooms) System.out.println(r);
    }

    private static void viewAllRooms() {
        System.out.println("--- All Rooms ---");
        for (Room r : hotel.getAllRooms()) System.out.println(r);
    }

    private static void registerGuest() {
        System.out.print("Enter guest name: ");
        String name = sc.nextLine();
        System.out.print("Enter phone number: ");
        String phone = sc.nextLine();
        System.out.print("Enter email: ");
        String email = sc.nextLine();
        Guest guest = hotel.registerGuest(name, phone, email);
        System.out.println("Registered successfully: " + guest);
    }

    private static void bookRoom() {
        if (hotel.getAllGuests().isEmpty()) {
            System.out.println("No guests registered yet. Please register a guest first (option 3).");
            return;
        }
        viewAllGuests();
        int guestId = readInt("Enter guest ID to book for: ");
        Guest guest = null;
        for (Guest g : hotel.getAllGuests()) {
            if (g.getGuestId() == guestId) { guest = g; break; }
        }
        if (guest == null) {
            System.out.println("Guest not found.");
            return;
        }
        viewAvailableRooms();
        int roomNumber = readInt("Enter room number to book: ");
        int nights = readInt("Enter number of nights: ");

        Reservation reservation = hotel.bookRoom(guest, roomNumber, nights);
        if (reservation != null) {
            System.out.println("Booking confirmed!");
            System.out.println(reservation);
        }
    }

    private static void checkout() {
        viewAllReservations();
        int reservationId = readInt("Enter reservation ID to checkout: ");
        Reservation res = hotel.findReservation(reservationId);
        if (res == null) {
            System.out.println("Reservation not found.");
            return;
        }
        boolean success = hotel.checkout(reservationId);
        if (success) {
            System.out.println("Checkout complete. Final bill for " + res.getGuest().getName()
                    + ": Rs." + res.getTotalAmount());
        } else {
            System.out.println("Reservation already checked out or invalid.");
        }
    }

    private static void viewAllReservations() {
        List<Reservation> reservations = hotel.getAllReservations();
        if (reservations.isEmpty()) {
            System.out.println("No reservations yet.");
            return;
        }
        System.out.println("--- All Reservations ---");
        for (Reservation r : reservations) System.out.println(r);
    }

    private static void viewAllGuests() {
        List<Guest> guests = hotel.getAllGuests();
        if (guests.isEmpty()) {
            System.out.println("No guests registered yet.");
            return;
        }
        System.out.println("--- Registered Guests ---");
        for (Guest g : guests) System.out.println(g);
    }

    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            sc.next();
        }
        int val = sc.nextInt();
        sc.nextLine(); // consume leftover newline
        return val;
    }
}
