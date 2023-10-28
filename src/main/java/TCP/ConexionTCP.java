package TCP;

import Utils.Utils;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Properties;

public class ConexionTCP {

    //Declare class variables
    private Socket socketClienteTCP;
    private ServerSocket socketServidorTCP;
    private int puertoCliente, puertoServidor;
    private String ipCliente, ipServidor;

    /*
    Constructor
     */
    public ConexionTCP(String ifServer) throws Exception {
        Properties prop = Utils.getConfigProperties();
        //Get properties from config.properties
        puertoServidor = Integer.parseInt(prop.getProperty("SERVER_PORT"));
        puertoCliente = Integer.parseInt(prop.getProperty("CLIENT_PORT"));
        ipServidor = prop.getProperty("SERVER_IP");
        ipCliente = prop.getProperty("CLIENT_IP");
    }

    /*
    Init connection
     */
    public void iniciarTCP() throws IOException {
        //Instance socket and serverSocket
        socketClienteTCP = new Socket(ipCliente,puertoCliente);
        socketServidorTCP = new ServerSocket(puertoServidor);
    }

    /*
    Send message
     */
    public void enviarMensajeTCP(Object mensaje) throws IOException {
        //Create output stream
        ObjectOutputStream streamEnviable = new ObjectOutputStream(socketClienteTCP.getOutputStream());
        //Send output stream
        streamEnviable.writeObject(mensaje);
        //Close stream
        streamEnviable.close();
    }

    /*
    Receive message
     */
    public String recibirMensajeTCP() throws IOException {
        //Accept connections
        socketClienteTCP = socketServidorTCP.accept();
        //Instance input stream
        DataInputStream streamInput = new DataInputStream(socketClienteTCP.getInputStream());
        //Return input message
        return streamInput.readUTF();
    }

    /*
    Close client
     */
    public void desconectarTCP() throws IOException {
        //Close connection
        socketClienteTCP.close();
    }

}
