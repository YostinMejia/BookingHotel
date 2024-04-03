package Hotel;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Room {
    private int roomNumber;
    private String[] beds;
    private double price_day;
    private int squares;
    private String[] description;
    private int number_of_guest;
    private int total_rooms;
    private Map<Integer, RoomStatus> rooms;

    public Room(int roomNumber, String[] beds, double price_day, int squares, String[] description, int number_of_guest, int total_rooms) {
        this.roomNumber = roomNumber;
        this.beds = beds;
        this.price_day = price_day;
        this.squares = squares;
        this.description = description;
        this.number_of_guest = number_of_guest;
        this.total_rooms = total_rooms;
        this.rooms = new HashMap<>();
        for (int i = 1; i <= total_rooms; i++) {
            rooms.put(i, new RoomStatus("available", new Reservation[0]));
        }
    }


    public int getRoomNumber() {
        return roomNumber;
    }

    public String[] getBeds() {
        return beds;
    }

    public double getPrice() {
        return price_day;
    }

    public int getSquares() {
        return squares;
    }

    public String[] getDescription() {
        return description;
    }

    public int getNumberOfGuests() {
        return number_of_guest;
    }

    public int getTotalRooms() {
        return total_rooms;
    }

    public Map<Integer, RoomStatus> getRooms() {
        return rooms;
    }

    public class RoomStatus {
        private String status;
        private Reservation[] reservations;

        public RoomStatus(String status, Reservation[] reservations) {
            this.status = status;
            this.reservations = reservations;
        }

        public String getStatus() {
            return status;
        }

        public Reservation[] getReservations() {
            return reservations;
        }

        public boolean dateReserved(Date from, Date to) {
            return Arrays.stream(reservations).noneMatch(reservation ->
                    (from.before(reservation.getTo()) || from.equals(reservation.getTo()))
                            && (to.after(reservation.getFrom()) || to.equals(reservation.getFrom())));
        }


    }
}
