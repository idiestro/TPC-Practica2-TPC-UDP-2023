package TCP;

import Utils.Utils;
import jdk.jshell.execution.Util;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {

    private ArrayList<ConnectionHandler> connections;
    private ServerSocket server;
    private boolean done;
    private ExecutorService pool;
    private String ipServidor;
    private int puertoServidor;

    /*
    Constructor
     */
    public Server() {
        connections = new ArrayList<>();
        done = false;
        try {
            //Get properties from config.properties
            Properties prop = Utils.getConfigProperties();
            puertoServidor = Integer.parseInt(prop.getProperty("SERVER_PORT"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /*
    Multithread run
     */
    @Override
    public void run() {
        try {
            //Init server and connections pool
            server = new ServerSocket(puertoServidor);
            pool = Executors.newCachedThreadPool();
            System.out.println("---Server Connected---");
            //Accept clients and hand connections
            while (!done) {
                Socket client = server.accept();
                ConnectionHandler handler = new ConnectionHandler(client);
                connections.add(handler);
                pool.execute(handler);
            }

        } catch (Exception e) {
            shutdown();
        }

    }

    /*
    Broadcast function
     */
    public void broadcast(String message) {
        for (ConnectionHandler ch : connections) {
            if (ch != null) {
                ch.sendMessage(message);
            }
        }
    }

    /*
    Close connections and execution
     */
    public void shutdown() {
        try {
            done = true;
            if (!server.isClosed()) {
                server.close();
            }
            for (ConnectionHandler ch : connections) {
                ch.shutdown();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /*
    Connection handler class
     */
    class ConnectionHandler implements Runnable {
        private Socket client;
        private BufferedReader in;
        private PrintWriter out;
        private String nickname;

        /*
        Constructor
         */
        public ConnectionHandler(Socket client) {
            this.client = client;
        }

        /*
        Multithread run
         */
        @Override
        public void run() {
            try {
                //Init input and output message
                out = new PrintWriter(client.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));

                //Ask for a nickname for user
                out.println("Inserta un nombre de usuario");
                //Save nickname
                nickname = in.readLine();
                System.out.println(nickname + " Conectado");
                //broadcast message to client with nickname and connections successfully
                broadcast(nickname + " disponible en la Wiki!");

                String message;

                //Message options
                while ((message = in.readLine()) != null) {
                    //Change Nick options
                    if (message.startsWith("/nick")) {
                        String[] messageSplit = message.split(" ", 2);
                        if (messageSplit.length == 2) {
                            broadcast(nickname + " se renombra como " + messageSplit[1]);
                            System.out.println(nickname + " se renombra como " + messageSplit[1]);
                            nickname = messageSplit[1];
                            out.println("Nick cambiado satisfactoriamente a " + nickname);
                        } else {
                            out.println("Nick no insertado");
                        }
                        //Close connection function
                    }else if (message.startsWith("/quit")) {
                        broadcast(nickname + " left the chat");
                        shutdown();
                    //Broadcast function
                    } else {
                        broadcast(nickname + ": " + message);
                    }
                }
            } catch (IOException e) {
                shutdown();
            }
        }

        /*
        Send message for client
         */
        public void sendMessage(String message) {
            out.println(message);
        }

        /*
        Close connection and execution
         */
        public void shutdown() {
            try {
                in.close();
                out.close();
                if (!client.isClosed()) {
                    client.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /*
    Launcher
     */
    public static void main(String[] args) {
        Server server = new Server();
        server.run();
    }

}

