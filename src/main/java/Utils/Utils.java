package Utils;

import java.io.FileInputStream;
import java.util.Properties;

public class Utils {
    public static Properties prop;

    /*
    Read and work with config.properties
     */
    public static Properties getConfigProperties() throws Exception{
        try {
            prop = new Properties();
            prop.load(new FileInputStream("config.properties"));

            return prop;
        }catch (Exception e){
            throw new Exception("No se encuentra el archivo de configuración: config.properties");

        }
    }
    /*
    Get received message and create a response message
     */
    public String getMessageAndCreateResponse(String mensajeIn){
        String mensajeOut = switch (mensajeIn) {
            case "Ravenclaw" -> "Has elegido la casa Azul";
            case "Hufflepuff" -> "Has elegido la casa Amarilla";
            case "Slytherin" -> "Has elegido la casa Verde";
            case "Gryffindor" -> "Has elegido la casa Roja";
            default -> "ERROR - El mensaje no ha podido ser analizado";
        };
        return mensajeOut;
    }
}
