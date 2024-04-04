package json;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import  Hotel.Hotel;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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

    // Método para guardar los cambios en el archivo JSON
    public static void saveToJson(String filePath, Hotel[] hotels) {
        Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create();

        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(hotels, writer);
            System.out.println("archivo JSON actualizado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

