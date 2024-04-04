package Hotel;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Room {
    private String room_id;
    private String status;
    private List<Reservation> reservations;

    public Room(String room_id, String status, List<Reservation> reservations) {
        this.room_id = room_id;
        this.status = status;
        this.reservations = reservations;
    }


    public void deleteReservation(Integer reservation_id) {
        for (int i = 0; i < reservations.size(); i++) {
            if (Objects.equals(reservations.get(i).getId(), reservation_id)) {
                reservations.remove(i);
                System.out.println("Reservación eliminada");
                return;
            }
        }
        System.out.println("La reserva no existe");
    }

    public void addReservation(Reservation reservation) {
        if (!dateReserved(reservation.getFrom(), reservation.getTo())) {
            this.reservations.add(reservation);
            return;
        }
        System.out.println("Fecha reservada");
    }

    public String getRoomId() {
        return room_id;
    }

    public String getStatus() {
        return status;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public boolean dateReserved(Date from, Date to) {
        for (Reservation reservation : reservations) {
            if (from.before(reservation.getTo()) && to.after(reservation.getFrom())) {
                return true;
            }
        }
        return false;
    }
}
