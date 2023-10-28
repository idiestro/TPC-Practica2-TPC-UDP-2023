package UDP;

import java.io.IOException;
import java.net.*;
import java.util.Properties;
import Utils.Utils;

public class ConexionUDP {

    private final boolean isServer;
    private final int puertoServidor;
    private final String ipServidor;
    private InetAddress direccionServidor;
    private DatagramSocket socketUDP;
    private final byte[] buffer_recibido = new byte[2024];

    /*
    Constructor
     */
    public ConexionUDP(String ifServer) throws Exception {
        Properties prop = Utils.getConfigProperties();
        puertoServidor = Integer.parseInt(prop.getProperty("SERVER_PORT"));
        ipServidor = prop.getProperty("SERVER_PIP");
        isServer = ifServer.equals("Servidor");
    }

    /*
    Init connection
     */
    public void iniciarUDP() throws SocketException, UnknownHostException {
        //Set client IP
        direccionServidor = InetAddress.getByName(ipServidor);
        //Create UDP socket
        if(isServer){
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
        //Declare Datagram for sent message
        DatagramPacket mensajeEnviado = new DatagramPacket(buffer_enviado, buffer_enviado.length, direccionServidor, puertoServidor);
        socketUDP.send(mensajeEnviado);
    }

    /*
    Receive message
     */
    public String recibirMensajeUDP() throws Exception {
        //Declare datagram for received message
        DatagramPacket mensajeRecibido = new DatagramPacket(buffer_recibido, buffer_recibido.length);
        //Client receive message
        socketUDP.receive(mensajeRecibido);
        //Return received message as string
        return new String(mensajeRecibido.getData());
    }

    /*
    Close client
     */
    public void desconectarUDP(){
        //Close client
        socketUDP.close();
    }

}
