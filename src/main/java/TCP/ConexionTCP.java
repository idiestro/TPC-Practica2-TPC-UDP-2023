package TCP;

import Utils.*;

import java.io.*;
import java.net.*;
import java.util.Properties;

public class ConexionTCP {

    //Declare class variables
    private Socket socketCliente;
    private ServerSocket socketServidor;
    private int puertoCliente, puertoServidor;
    private final String ipCliente, ipServidor;
    private final boolean isServer;


    /*
    Constructor
     */
    public ConexionTCP(Boolean Servidor) throws Exception {
        Properties prop = Utils.getConfigProperties();
        //Get properties from config.properties
        puertoServidor = Integer.parseInt(prop.getProperty("SERVER_PORT"));
        puertoCliente = Integer.parseInt(prop.getProperty("CLIENT_PORT"));
        ipServidor = prop.getProperty("SERVER_IP");
        ipCliente = prop.getProperty("CLIENT_IP");
        isServer = Servidor;

    }


    /*
    Init connection
     */
    public void iniciarTCP() throws IOException {
        //Instance socket and serverSocket
        if(isServer){
            this.socketServidor = new ServerSocket(puertoServidor);
        } else {
            this.socketCliente = new Socket(ipServidor, puertoServidor);
        }
    }

    public void aceptarConexionTCP() throws IOException {
        //Accept connections
        this.socketCliente = socketServidor.accept();
    }

    /*
        Send message
         */
    public void enviarMensajeTCP(String mensajeEnviado) throws IOException {
        if (isServer) {
            this.socketCliente = new Socket(ipCliente, puertoCliente);
            DataOutputStream streamOutput = new DataOutputStream(socketCliente.getOutputStream());
            streamOutput.writeUTF(mensajeEnviado);
            streamOutput.flush();
            streamOutput.close();
        } else {
            ObjectStreamGenerator objectOutput = new ObjectStreamGenerator();
            objectOutput.setIp(ipCliente);
            objectOutput.setPayload(mensajeEnviado);
            ObjectOutputStream streamOutput = new ObjectOutputStream(socketCliente.getOutputStream());
            streamOutput.writeObject(objectOutput);
            streamOutput.close();
        }
    }

    /*
    Receive message
     */
    public String recibirMensajeTCP() throws IOException, ClassNotFoundException {
        String mensajeRecibido = null;
        if(isServer){
            //Instance input stream
            ObjectInputStream streamInput = new ObjectInputStream(socketCliente.getInputStream());
            ObjectStreamGenerator objectInput = (ObjectStreamGenerator) streamInput.readObject();
            mensajeRecibido = objectInput.getPayload();

        } else {
            //Create server socket
            this.socketServidor = new ServerSocket(puertoCliente);
            //Accept connections
            this.socketCliente = socketServidor.accept();

            //Instance input stream
            DataInputStream streamInput = new DataInputStream(socketCliente.getInputStream());
            //Save input message
            mensajeRecibido = streamInput.readUTF();
        }
        return mensajeRecibido;
    }

    /*
    Close client
     */
    public void desconectarTCP() throws IOException {
        //Close connection
        socketServidor.close();
        socketCliente.close();
    }

}
