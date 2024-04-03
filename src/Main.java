import Hotel.Hotel;
import json.Parser;
import Hotel.Room;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Crear un nuevo usuario
        Hotel[] hotels = Parser.parseJson("./src/hotel.json");
        //Parser.printHotels(hotels);
        Hotel[] hotels_finded = Parser.query("Laureles", hotels).toArray(new Hotel[0]);
        //Parser.printHotels(hotels_finded);
        List<Room> rooms_available = Parser.filter(hotels_finded, new Date(),new Date(2024,4,5),2);
        Parser.printRooms(rooms_available);

        // Agregar el nuevo usuario al archivo JSON
    }

}
