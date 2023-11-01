package Launcher.TCP;

import TCP.*;

import java.net.DatagramPacket;
import java.nio.charset.StandardCharsets;


public class ServerTCP extends Thread{

    private static ConexionTCP conexionTCP;

    public void run(){

        while (true) {
            try {
                String mensajeReicbido = conexionTCP.recibirMensajeTCP();
                System.out.println(mensajeReicbido);
                conexionTCP.enviarMensajeTCP("Hola cliente");

            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        try {
            //Launch Server
            conexionTCP = new ConexionTCP(true);

            conexionTCP.iniciarTCP();
            System.out.println("----Servidor Iniciado----");

            //Execute multiThreads instructions
            ServerTCP serverTCP = new ServerTCP();
            serverTCP.start();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}