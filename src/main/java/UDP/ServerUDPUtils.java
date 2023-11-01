package UDP;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashSet;

public class ServerUDPUtils {

    private static ArrayList <InetAddress> direccionesClientes;
    private static ArrayList <Integer> puertosClientes;
    private static HashSet <String> listaClientes;

    private int puertoCliente;
    private InetAddress direccionCliente;
    private String Id;

    /*
    Constructor
     */
    public ServerUDPUtils(){
        //Initial arrays
        direccionesClientes = new ArrayList<>();
        puertosClientes = new ArrayList<>();
        listaClientes = new HashSet<>();
    }

    /*
    Get Client Information
     */
    public void getClientInfo(DatagramPacket mensajeRecibido){
        //Port
        puertoCliente = mensajeRecibido.getPort();
        //IP Address
        direccionCliente = mensajeRecibido.getAddress();
    }

    /*
    Save Client information
     */
    public void saveClientInfo(){
        //Create identifier
        Id = direccionCliente.toString()+"-"+puertoCliente;
        //Save Id if not saved before
        if(!listaClientes.contains(Id)){
            listaClientes.add(Id);
            puertosClientes.add(puertoCliente);
            direccionesClientes.add(direccionCliente);
        }
    }

    /*
    Send log for new client connection
     */
    public void logClientInfo(){
        System.out.println("Cliente conectado: " + Id);
    }
}
