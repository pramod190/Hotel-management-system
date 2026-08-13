public class Room {
    private int roomNumber;
    private String type;       // e.g. Single, Double, Suite
    private double pricePerNight;
    private boolean available;

    public Room(int roomNumber, String type, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.pricePerNight = pricePerNight;
        this.available = true;
    }

    public int getRoomNumber() { return roomNumber; }
    public String getType() { return type; }
    public double getPricePerNight() { return pricePerNight; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return "Room #" + roomNumber + " [" + type + "] - Rs." + pricePerNight + "/night - "
                + (available ? "Available" : "Occupied");
    }
}
