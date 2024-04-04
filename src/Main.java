import Hotel.*;
import json.Parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String filePath ="./src/hotel.json";
        start(filePath);
    }

    public static void  start(String filePath){
        Hotel[] hotels = Parser.parseJson(filePath);
        Scanner scanner = new Scanner(System.in);



        try {
            int opcion;
            do {
                System.out.println("Menu:");
                System.out.println("1. Ver hoteles disponibles para reservar");
                System.out.println("2. Cancelar reservación");
                System.out.println("3. Ver todos los hoteles ");
                System.out.println("4. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:


                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                        System.out.println("Ingrese la fecha de llegada yyyy-MM-dd:");
                        Date from = dateFormat.parse(scanner.next());
                        System.out.println("Ingrese la fecha de salida yyyy-MM-dd:");
                        Date to = dateFormat.parse(scanner.next());

                        List<Hotel> foundHotels = buscar(scanner, hotels, from, to);
                        if (!foundHotels.isEmpty()) {
                            System.out.println("Hoteles encontrados:");
                            for (Hotel hotel : foundHotels) {
                                hotel.printHotel();
                            }

                            System.out.println("Ingrese el id del hotel para ver las habitaciones disponibles:");
                            int id = scanner.nextInt();
                            for (Hotel hotel : foundHotels) {
                                if (hotel.getId() == id) {
                                    hotel.printHotel();
                                    for (TypeRoom typeRoom : hotel.getTypeRooms()) {
                                        typeRoom.printRoom();
                                    }

                                    System.out.println("Ingrese el tipo de habitación que desea reservar:");
                                    String typeRoom = scanner.next();
                                    scanner.nextLine();
                                    System.out.print("Ingrese su nombre: ");
                                    String nombre = scanner.nextLine();
                                    reservation(hotel, typeRoom, nombre, from, to);
                                    Parser.saveToJson(filePath, hotels);
                                    break;
                                }
                            }
                        } else {
                            System.out.println("No se encontraron hoteles que coincidan con la búsqueda.");
                        }
                        break;
                    case 2:
                        // Leer hotel_id
                        System.out.print("Ingrese el ID del hotel: ");
                        int hotel_id = scanner.nextInt();
                        scanner.nextLine(); // Consumir el carácter de nueva línea

                        System.out.print("Ingrese el tipo de habitación: ");
                        String type_room = scanner.nextLine();

                        System.out.print("Ingrese el ID de la habitación: ");
                        String room_id = scanner.nextLine();

                        System.out.print("Ingrese el ID de la reserva: ");
                        int reservation_id = scanner.nextInt();
                        scanner.nextLine(); // Consumir el carácter de nueva línea después del número entero
                        cancelateReserve(hotels, hotel_id, type_room, room_id, reservation_id);
                        Parser.saveToJson(filePath, hotels);
                        break;
                    case 3:
                        Arrays.stream(hotels)
                                .forEach(hotel -> hotel.printHotel());
                        break;
                    case 4:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                }
            } while (opcion != 4);

            scanner.close();
        }catch (Error | ParseException e){
            e.printStackTrace();
        }
    }

    public static List<Hotel> buscar(Scanner scanner, Hotel[] hotels, Date from, Date to) {
        System.out.println("Ingrese el nombre o ubicación o ciudad o país del hotel o lugar dónde desea reservar:");
        String query = scanner.next();
        Hotel[] foundHotels = Search.query(query, hotels).toArray(new Hotel[0]);

        try {

            System.out.println("Ingrese la cantidad de personas:");
            int persons = scanner.nextInt();

            return Search.filter(foundHotels, from, to, persons);
        } catch (Error e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void reservation(Hotel hotel, String typeRoom, String nombre, Date from, Date to) {
        Optional<Reservation> reservation;
        for (TypeRoom room: hotel.getTypeRooms()){
            if (room.getType().equals(typeRoom)){
                reservation = hotel.reservate(typeRoom,  room.getRooms().get(0).getRoomId(), new Reservation(room.getRooms().get(0).getReservations().size()+1,nombre, from, to));
                if (reservation.isPresent()) {
                    System.out.println("Reserva realizada con éxito.");
                } else {
                    System.out.println("No se pudo realizar la reserva.");
                }
            }
            break;

        }

    }

    public static void cancelateReserve(Hotel[] hotels, int hotel_id, String typeRoom, String room_id, int reservation_id) {
        for (Hotel hotel : hotels) {
            if (hotel.getId() == hotel_id) {
                hotel.cancelReservation(typeRoom, room_id, reservation_id);
                return;
            }
        }
        System.out.println("No se encontró el hotel para cancelar la reserva.");
    }

}
