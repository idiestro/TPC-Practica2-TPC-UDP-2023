package UDP;

import java.io.IOException;
import java.net.*;
import java.util.Properties;

import Launcher.UDP.*;
import Utils.Utils;

public class ConexionUDP {

    //Declare class variables
    private final boolean isServer;
    private final int puertoServidor;
    private final String ipServidor;
    private InetAddress direccionServidor;
    private static DatagramSocket socketUDP;
    private final byte[] buffer_recibido = new byte[2024];

    /*
    Constructor
     */
    public ConexionUDP(String ifServer) throws Exception {
        //Get properties from config.properties
        Properties prop = Utils.getConfigProperties();
        puertoServidor = Integer.parseInt(prop.getProperty("SERVER_PORT"));
        ipServidor = prop.getProperty("SERVER_IP");
        //Select Server or Client connection
        isServer = ifServer.equals("Servidor");
    }

    /*
    Init connection
     */
    public void iniciarUDP() throws SocketException, UnknownHostException {
        //Set client IP
        direccionServidor = InetAddress.getByName(ipServidor);
        //Create UDP socket
        if (isServer) {
            socketUDP = new DatagramSocket(puertoServidor);
        } else {
            socketUDP = new DatagramSocket();
        }

    }

    /*
    Send message
     */
    public void enviarMensajeUDP(String mensaje) throws IOException {
        //Declare buffer for sent message
        byte[] buffer_enviado = mensaje.getBytes();

        if (isServer) {
            for (int i = 0; i < ServerUDPUtils.listaClientes.size(); i++) {
                DatagramPacket respuesta = new DatagramPacket(buffer_enviado, buffer_enviado.length, ServerUDPUtils.direccionesClientes.get(i), ServerUDPUtils.puertosClientes.get(i));
                socketUDP.send(respuesta);
            }
        } else {
            //Declare Datagram for sent message
            DatagramPacket mensajeEnviado = new DatagramPacket(buffer_enviado, buffer_enviado.length, direccionServidor, puertoServidor);
            socketUDP.send(mensajeEnviado);
        }


    }

    /*
    Receive message
     */
    public DatagramPacket recibirMensajeUDP() throws Exception {
        //Declare datagram for received message
        DatagramPacket mensajeRecibido = new DatagramPacket(buffer_recibido, buffer_recibido.length);
        //Client receive message
        socketUDP.receive(mensajeRecibido);
        //Return received message
        return mensajeRecibido;
    }

    /*
    Close client
     */
    public void desconectarUDP() {
        //Close client
        socketUDP.close();
    }

}
