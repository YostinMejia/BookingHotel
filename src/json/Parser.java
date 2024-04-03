package json;

import  Hotel.Hotel;
import Hotel.Room;
import Hotel.Room.RoomStatus;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {
    public static Hotel[] parseJson(String filePath) {
        try {
            // Leer el archivo JSON y parsearlo en un array de objetos Hotel
            return new Gson().fromJson(new FileReader(filePath), Hotel[].class);

        }catch (Error e){
            e.printStackTrace();
            return new Hotel[0];
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<Hotel> query(String query, Hotel[] hotels){
        return Arrays.stream(hotels).filter(hotel -> hotel.getName().contains(query)
                || hotel.getCity().equalsIgnoreCase(query)
                || hotel.getState().equalsIgnoreCase(query)
                || hotel.getLocation().contains(query))
                .collect(Collectors.toList());
    }
    public static  List<Room> filter(Hotel[] hotels,Date from, Date to, Integer persons){
        List<Room> filtered = new ArrayList<>();
        for (Hotel hotel : hotels) {
            for (Room typeRoom : hotel.getTypeRooms()) {
                System.out.println(typeRoom.getNumberOfGuests());

                if (typeRoom.getNumberOfGuests() >= persons) {
                    for (RoomStatus room : typeRoom.getRooms().values()) {
                        if (room.getStatus().equalsIgnoreCase("available") && room.dateReserved(from, to)) {
                            filtered.add(typeRoom);
                        }
                    }
                }
            }
        }
        System.out.println(filtered.stream().count());
        return filtered;
    }
    public static void printRooms(List<Room> rooms) {

            for (Room room : rooms) {
                System.out.println("  Número de habitación: " + room.getRoomNumber());
                System.out.println("  Precio: " + room.getPrice());
                System.out.println("  Metros cuadrados: " + room.getSquares());
                System.out.println("  Descripción: ");
                for (String desc : room.getDescription()) {
                    System.out.println("    " + desc);
                }
                System.out.println("  Número de huéspedes: " + room.getNumberOfGuests());
                System.out.println("  Total de habitaciones: " + room.getTotalRooms());
            }
    }


    public static void printHotels(Hotel[] hotels) {

        // Iterar sobre los hoteles y mostrar su información
        for (Hotel hotel : hotels) {
            System.out.println("Hotel: " + hotel.getName());
            System.out.println("Ubicación: " + hotel.getLocation());
            System.out.println("País: " + hotel.getCountry());
            System.out.println("Estado: " + hotel.getState());
            System.out.println("Ciudad: " + hotel.getCity());
            System.out.println("Contacto: " + hotel.getContact().getPhone());
            System.out.println("Habitaciones:");
            for (Room room : hotel.getTypeRooms()) {
                System.out.println("  Número de habitación: " + room.getRoomNumber());
                System.out.println("  Precio: " + room.getPrice());
                System.out.println("  Metros cuadrados: " + room.getSquares());
                System.out.println("  Descripción: ");
                for (String desc : room.getDescription()) {
                    System.out.println("    " + desc);
                }
                System.out.println("  Número de huéspedes: " + room.getNumberOfGuests());
                System.out.println("  Total de habitaciones: " + room.getTotalRooms());
            }
        }
    }
}
