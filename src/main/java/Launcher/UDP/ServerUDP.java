package Launcher.UDP;

import UDP.ConexionUDP;


public class ServerUDP extends Thread{

    private static ConexionUDP conexionUDP;

    public void run(){
        try {
            while (true) {
                String mensajeRecibido = conexionUDP.recibirMensajeUDP();
                System.out.println(mensajeRecibido);
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