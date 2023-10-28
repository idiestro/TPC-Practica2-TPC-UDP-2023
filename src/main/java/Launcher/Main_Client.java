package Launcher;

import UDP.conexionUDP;

public class Main_Client {

    public static void main(String[] args) {

        try {
            conexionUDP ConexionUDP = new conexionUDP("Cliente");
            ConexionUDP.iniciarUDP();
            System.out.println("----Cliente Iniciado----");
            ConexionUDP.enviarMensajeUDP("Hola desde el cliente");
            ConexionUDP.desconectarUDP();
            System.out.println("----Cliente Cerrado----");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}