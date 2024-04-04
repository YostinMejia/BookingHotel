import Hotel.Hotel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import Hotel.TypeRoom;

public  class Search {
    public static List<Hotel> query(String query, Hotel[] hotels){
        return Arrays.stream(hotels).filter(hotel -> hotel.getName().contains(query)
                        || hotel.getCity().equalsIgnoreCase(query)
                        || hotel.getState().equalsIgnoreCase(query)
                        || hotel.getLocation().contains(query))
                .collect(Collectors.toList());
    }
    public static  List<Hotel> filter(Hotel[] hotels, Date from, Date to, Integer persons){
        return Arrays.stream(hotels)
                .map(hotel -> {
                    Hotel tempHotel = new Hotel(hotel.getId(), hotel.getName(), hotel.getLocation(), hotel.getCountry(), hotel.getState(),
                            hotel.getCity(), hotel.getContact(), new ArrayList<>());

                    hotel.getTypeRooms().stream()
                            .filter(typeRoom -> typeRoom.getNumberOfGuests() == persons)
                            .forEach(typeRoom -> {
                                TypeRoom tempTypeRoom = new TypeRoom(typeRoom.getType(), typeRoom.getBeds(), typeRoom.getPriceDay(),
                                        typeRoom.getSquares(), typeRoom.getDescription(), typeRoom.getNumberOfGuests(),
                                        typeRoom.getTotalRooms(), new ArrayList<>());

                                typeRoom.getRooms().stream()
                                        .filter(room -> room.getStatus().equals("available")&&!room.dateReserved(from, to))
                                        .forEach(tempTypeRoom.getRooms()::add);

                                if (!tempTypeRoom.getRooms().isEmpty()) {
                                    tempHotel.getTypeRooms().add(tempTypeRoom);
                                }
                            });

                    return tempHotel;
                })
                .filter(hotel -> !hotel.getTypeRooms().isEmpty())
                .toList();
    }

}
