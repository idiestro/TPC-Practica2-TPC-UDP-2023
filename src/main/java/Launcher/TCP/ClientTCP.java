package Launcher.TCP;

import TCP.ConexionTCP;


public class ClientTCP extends Thread{

    private static ConexionTCP conexionTCP;

    public void run(){

        while (true) {
            try {
                conexionTCP.enviarMensajeTCP("Hola servidor");
                String mensajeRecibido = conexionTCP.recibirMensajeTCP();
                System.out.println(mensajeRecibido);

            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        try {
            //Launch Server
            conexionTCP = new ConexionTCP(false);

            conexionTCP.iniciarTCP();
            System.out.println("----Cliente Iniciado----");

            //Execute multiThreads instructions
            ClientTCP serverTCP = new ClientTCP();
            serverTCP.start();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}