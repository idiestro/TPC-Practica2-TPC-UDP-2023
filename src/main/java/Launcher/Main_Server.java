package Launcher;

import UDP.conexionUDP;

import java.net.SocketException;
import java.net.UnknownHostException;

public class Main_Server extends Thread{

    private static conexionUDP ConexionUDP;

    public void run(){
        try {
            while(true){
                String mensajeRecibido = ConexionUDP.recibirMensajeUDP();
                System.out.println(mensajeRecibido);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        try {
            //Launch Server
            ConexionUDP = new conexionUDP("Servidor");
            ConexionUDP.iniciarUDP();
            System.out.println("----Servidor Iniciado----");

            //Execute multiThreads instructions
            Main_Server main_server = new Main_Server();
            main_server.start();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}