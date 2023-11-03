package Launcher.TCP;

import TCP.*;

import java.net.DatagramPacket;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


public class ServerTCP implements Runnable {

    private static ConexionTCP conexionTCP;
    private Socket socketCliente;
    private ServerSocket socketServidor;

    public void run() {
        try {
            while (true) {
                conexionTCP.aceptarConexionTCP();
                String mensajeReicbido = conexionTCP.recibirMensajeTCP();
                System.out.println(mensajeReicbido);
                conexionTCP.enviarMensajeTCP("Hola servidor");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        try {
            //Launch Server
            conexionTCP = new ConexionTCP(true);
            conexionTCP.iniciarTCP();


            System.out.println("----Servidor Iniciado----");

            //Execute multiThreads instructions
            Thread serverTCP = new Thread(new ServerTCP());
            serverTCP.start();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}