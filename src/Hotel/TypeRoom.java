package Hotel;

import java.util.*;
public class TypeRoom {
    private String type;
    private String[] beds;
    private double price_day;
    private int squares;
    private String[] description;
    private int number_of_guest;
    private int total_rooms;
    private List<Room> rooms;

    public TypeRoom(String type, String[] beds, double price_day, int squares, String[] description, int number_of_guest, int total_rooms, List<Room> rooms) {
        this.type = type;
        this.beds = beds;
        this.price_day = price_day;
        this.squares = squares;
        this.description = description;
        this.number_of_guest = number_of_guest;
        this.total_rooms = total_rooms;
        this.rooms = rooms;
    }

    public void cancelReservation(String room_id, Integer reservation_id) {
        for (Room room : rooms) {
            if (room.getRoomId().equals(room_id)) {
                room.deleteReservation(reservation_id);
                return;
            }
        }
        System.out.println("Habitación no encontrada.");
    }

    public Optional<Reservation> reservate(String room_id, Reservation reservation) {
        for (Room room : rooms) {
            if (room.getRoomId().equals(room_id)) {
                room.addReservation(reservation);
                return Optional.of(reservation);
            }
        }
        System.out.println("room_id no encontrada");

        return Optional.empty();
    }
    public void printRoom() {

        System.out.println("Tipo de habitación: " + getType());
        System.out.println("Precio por día: " + getPriceDay());
        System.out.println("Metros cuadrados: " + getSquares());
        System.out.println("Descripción:");
        for (String desc : getDescription()) {
            System.out.println("  " + desc);
        }
        System.out.println("Número de huéspedes: " + getNumberOfGuests());
        System.out.println("Total de habitaciones: " + getTotalRooms());
        System.out.println("Habitaciones:");
        for (Room status : getRooms()) {
            System.out.println("  ID de habitación: " + status.getRoomId());
            System.out.println("  Estado: " + status.getStatus());
            System.out.println("  Reservas:");
            for (Reservation reservation : status.getReservations()) {
                System.out.println("    Usuario: " + reservation.getUserInfo());
                System.out.println("    Desde: " + reservation.getFrom());
                System.out.println("    Hasta: " + reservation.getTo());
            }
        }
        System.out.println();

    }

    public String getType() {
        return type;
    }

    public double getPriceDay() {
        return price_day;
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

    public List<Room> getRooms() {
        return rooms;
    }
}
