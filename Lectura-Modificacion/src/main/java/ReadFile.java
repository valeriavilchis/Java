import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;

public class ReadFile {
    public static void main(String[] args) throws IOException, ParseException {
        String pathFolder = "/ruta/de/tu/archivo/";
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(pathFolder+"/Input"))) {
            for (Path file : stream) {
                Path fileName = file.getFileName();
                String fileString = pathFolder+"/Input/"+fileName.toString();
                Object objectJsonParser = new JSONParser().parse(new FileReader(fileString));
                JSONObject objectJsonRead = (JSONObject)objectJsonParser;
                JSONArray arrayOrder = (JSONArray) objectJsonRead.get("pricnipalKey");
                for (int i = 0; i <arrayOrder.size(); i++) {
                    JSONObject DebitEntity = (JSONObject) arrayOrder.get(i);
                    DebitEntity.remove("key");
                }
                String pathOutput = pathFolder+"/Output/"+fileName;
                try(PrintWriter outFile = new PrintWriter(new FileWriter(pathOutput))) {
                    outFile.write(objectJsonRead.toJSONString());
                } catch (Exception error){
                    error.printStackTrace();
                }
                System.out.println("Se guardó correctamente.");
            }
        } catch (IOException | DirectoryIteratorException ex) {
            System.err.println(ex);
        }
    }
}