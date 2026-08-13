# Hotel Management System (Console-based, Java)

A simple console-based Hotel Management System built in core Java, using
plain classes/collections (no database) to keep the design easy to follow
and extend.

## UML Class Diagram (simplified, text form)

```
+------------------+        +------------------+        +----------------------+
|      Room        |        |      Guest       |        |     Reservation      |
+------------------+        +------------------+        +----------------------+
| - roomNumber:int |        | - guestId:int    |        | - reservationId:int  |
| - type:String    |        | - name:String    |        | - guest:Guest        |
| - pricePerNight   |        | - phone:String   |        | - room:Room          |
| - available:bool |        | - email:String   |        | - nights:int         |
+------------------+        +------------------+        | - totalAmount:double |
| + getters/setters|        | + getters        |        | - status:String      |
+------------------+        +------------------+        +----------------------+
         ^                          ^                              ^
         |                          |                              |
         |  1                       |  1                           |
         +----------- uses ---------+------------- uses ------------+
                                     |
                                     |  1
                             +---------------+
                             |     Hotel     |
                             +---------------+
                             | - name:String |
                             | - rooms:List<Room>        |
                             | - guests:List<Guest>      |
                             | - reservations:List<Reservation> |
                             +---------------+
                             | + addRoom()               |
                             | + registerGuest()         |
                             | + bookRoom()               |
                             | + checkout()               |
                             | + getAvailableRooms()      |
                             | + getAllReservations()     |
                             +---------------+
                                     ^
                                     | uses
                             +---------------+
                             |     Main      |
                             +---------------+
                             | console menu  |
                             | + main()      |
                             +---------------+
```

**Relationships**
- `Hotel` *has-many* `Room`, `Guest`, `Reservation` (composition — Hotel owns and manages these collections).
- `Reservation` *associates* one `Guest` with one `Room` for a number of nights.
- `Main` is the console entry point that drives `Hotel`'s operations through a menu.

## Files
- `src/Room.java` — room entity (number, type, price, availability)
- `src/Guest.java` — guest entity (id, name, phone, email)
- `src/Reservation.java` — links a Guest + Room + stay duration + bill + status
- `src/Hotel.java` — service layer: manages rooms/guests/reservations, booking & checkout logic
- `src/Main.java` — console UI (menu-driven) that wires everything together

## How to Compile & Run

From the project root:

```bash
cd src
javac *.java
java Main
```

## Features
1. View available rooms
2. View all rooms (available + occupied)
3. Register a new guest
4. Book a room for a registered guest (auto-calculates total based on nights × price)
5. Checkout — closes the reservation and frees the room
6. View all reservations (with status: BOOKED / CHECKED_OUT)
7. View all registered guests

Sample rooms are pre-seeded (101/102 Single, 201/202 Double, 301 Suite) so
you can try booking immediately.

## Notes on extending this
- Swap the in-memory `ArrayList`s in `Hotel.java` for a database (e.g. JDBC + MySQL) to persist data.
- Add an `Admin` class with authentication if you need role-based access.
- Add input validation for dates instead of raw "nights" if you want actual check-in/check-out dates.
