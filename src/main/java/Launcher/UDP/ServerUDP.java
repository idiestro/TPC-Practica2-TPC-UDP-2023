package Launcher.UDP;

import UDP.*;
import Utils.Utils;

import java.net.DatagramPacket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;


public class ServerUDP extends Thread {

    private static ConexionUDP conexionUDP;
    private static ServerUDPUtils serverUDPUtils;
    private static Utils utils;

    public void run() {
        String mensajeRecibido;
        String mensajeOut;
        try {
            conexionUDP.iniciarUDP();
            System.out.println("----Servidor Iniciado----");

            while (true) {
                //Receive message from client
                DatagramPacket datagramIn = conexionUDP.recibirMensajeUDP();
                //Get, save and show client info: Id
                serverUDPUtils.getClientInfo(datagramIn);
                serverUDPUtils.saveClientInfo();
                serverUDPUtils.logClientInfo();
                //Parse datagram input into string
                mensajeRecibido = (new String(datagramIn.getData(), 0, datagramIn.getLength(), StandardCharsets.UTF_8));
                //Create a response based on a received message
                mensajeOut = utils.getMessageAndCreateResponse(mensajeRecibido);
                //Send message to client
                conexionUDP.enviarMensajeUDP(mensajeOut);
            }
        } catch (Exception e) {
            conexionUDP.desconectarUDP();
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        try {
            //Launch Server
            conexionUDP = new ConexionUDP("Servidor");
            serverUDPUtils = new ServerUDPUtils();
            utils = new Utils();

            //Execute multiThreads instructions
            ServerUDP serverUDP = new ServerUDP();
            serverUDP.start();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}