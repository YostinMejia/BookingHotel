package Hotel;

import java.util.List;
import java.util.Optional;

public class Hotel {
    private int id;
    private String name;
    private String location;
    private String country;
    private String state;
    private String city;
    private ContactInfo contact;
    public List<TypeRoom> type_rooms;

    public Hotel(int id, String name, String location, String country, String state, String city, ContactInfo contact,List<TypeRoom> type_rooms) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.country = country;
        this.state = state;
        this.city = city;
        this.contact = contact;
        this.type_rooms = type_rooms;
    }

    public void printHotel() {

        // Iterar sobre los hoteles y mostrar su información
        System.out.println("Id: " + getId());
        System.out.println("Hotel: " + getName());
        System.out.println("Ubicación: " + getLocation());
        System.out.println("País: " + getCountry());
        System.out.println("Estado: " + getState());
        System.out.println("Ciudad: " + getCity());
        System.out.println("Prefijo: " + getContact().getPrefix());
        System.out.println("Contacto: " + getContact().getPhone());

        System.out.println();

    }

    public void cancelReservation(String typeRoom, String room_id, Integer reservation_id) {
        for (TypeRoom room : getTypeRooms()) {
            if (room.getType().equals(typeRoom)) {
                room.cancelReservation(room_id, reservation_id);
                return;
            }
        }
        System.out.println("Tipo de habitación no encontrado.");
    }
    public Optional<Reservation> reservate(String type_room, String room_id, Reservation reservation){
        for (TypeRoom typeRoom : getTypeRooms()) {
            if(typeRoom.getType().equalsIgnoreCase(type_room)){
                typeRoom.reservate(room_id,reservation);
                return Optional.ofNullable(reservation);
            }
        }
        System.out.println("Tipo de habitación no encontrada");
        return Optional.<Reservation>empty();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public ContactInfo getContact() {
        return contact;
    }

    public List<TypeRoom> getTypeRooms() {
        return type_rooms;
    }

}
