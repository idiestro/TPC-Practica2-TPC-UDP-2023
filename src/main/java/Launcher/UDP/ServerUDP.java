package Launcher.UDP;

import UDP.*;

import java.net.DatagramPacket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;


public class ServerUDP extends Thread{

    private static ConexionUDP conexionUDP;
    private static ServerUDPUtils serverUDPUtils;

    public void run(){

        while (true) {
            try {
                DatagramPacket mensajeRecibido = conexionUDP.recibirMensajeUDP();

                serverUDPUtils.getClientInfo(mensajeRecibido);
                serverUDPUtils.saveClientInfo();
                serverUDPUtils.logClientInfo();

                System.out.println(new String(mensajeRecibido.getData(), 0, mensajeRecibido.getLength(), StandardCharsets.UTF_8));

            } catch (Exception e) {
                conexionUDP.desconectarUDP();
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        try {
            //Launch Server
            conexionUDP = new ConexionUDP("Servidor");
            serverUDPUtils =  new ServerUDPUtils();

            conexionUDP.iniciarUDP();
            System.out.println("----Servidor Iniciado----");

            //Execute multiThreads instructions
            ServerUDP serverUDP = new ServerUDP();
            serverUDP.start();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}