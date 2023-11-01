package Launcher.TCP;

import TCP.*;


public class ClientTCP {

    private static ConexionTCP conexionTCP;

    public static void main(String[] args) {
        try {
            //Launch Server
            conexionTCP = new ConexionTCP(false);

            conexionTCP.iniciarTCP();
            System.out.println("----Cliente Iniciado----");

            conexionTCP.enviarMensajeTCP("Hola servidor");

            String mensajeRecibido = conexionTCP.recibirMensajeTCP();
            System.out.println("----Cliente Desconectado----");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}