package Launcher;

import UDP.ConexionUDP;
import Utils.UserInterface;

public class Main_Client {

    public static void main(String[] args) {

        try {
            ConexionUDP conexionUDP = new ConexionUDP("Cliente");
            UserInterface userInterface = new UserInterface();

            conexionUDP.iniciarUDP();
            System.out.println("----Cliente Iniciado----");

            userInterface.iniciarUi();
            String opcionElegida = userInterface.seleccionarOpcionesUi();

            conexionUDP.enviarMensajeUDP(opcionElegida);

            userInterface.cerrarUI();
            conexionUDP.desconectarUDP();
            System.out.println("----Cliente Cerrado----");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}