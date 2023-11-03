package Launcher.UDP;

import UDP.ConexionUDP;
import Utils.UserInterface;

public class ClientUDP {

    public static void main(String[] args) {
        String mensajeRecibido;

        try {
            //Initial conection UDP and user interface (UI)
            ConexionUDP conexionUDP = new ConexionUDP("Cliente");
            UserInterface userInterface = new UserInterface();
            //Init Client
            conexionUDP.iniciarUDP();
            System.out.println("----Cliente Iniciado----");
            //Init UI
            userInterface.iniciarUi();

            //Client selects message options to send
            String opcionElegida = userInterface.seleccionarOpcionesUi();
            //Client send message selected
            conexionUDP.enviarMensajeUDP(opcionElegida);
            //Receive message from Server and save it
            mensajeRecibido = new String(conexionUDP.recibirMensajeUDP().getData());
            //Show message from server into UI
            userInterface.mostrarMensajeUi(mensajeRecibido);

            //Close UI
            userInterface.cerrarUI();
            //Close client socket
            conexionUDP.desconectarUDP();
            System.out.println("----Cliente Cerrado----");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}