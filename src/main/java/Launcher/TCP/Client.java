package Launcher.TCP;

import java.io.*;
import java.net.Socket;
import java.util.Properties;

import Utils.*;


public class Client implements Runnable {

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private boolean done;
    private String ipServidor;
    private int puertoServidor;


    public Client() {
        try {
            //Get properties from config.properties
            Properties prop = Utils.getConfigProperties();
            puertoServidor = Integer.parseInt(prop.getProperty("SERVER_PORT"));
            ipServidor = prop.getProperty("SERVER_IP");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public void run() {

        try {
            //Init client socket
            client = new Socket(ipServidor, puertoServidor);
            //Init user interface (UI)
            new TextIO4GUI("WikiHogwarts");
            //Init output message
            out = new PrintWriter(client.getOutputStream(), true);
            //Init buffered reader and input menssage
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            //Init input Handler multithreads
            InputHandler inHandler = new InputHandler();
            Thread thread = new Thread(inHandler);
            thread.start();

            //If input message !null, show it
            String messageIn;
            while ((messageIn = in.readLine()) != null) {
                System.out.println(messageIn);
                TextIO4GUI.putln(messageIn);
            }

        } catch (IOException e) {
            shutdown();
            System.out.println(e.getMessage());
        }
    }

    public void shutdown() {
        done = true;
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


    class InputHandler implements Runnable {

        @Override
        public void run() {
            try {
                while (!done) {
                    BufferedReader inReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream((TextIO4GUI.getlnString().getBytes()))));
                    String message = inReader.readLine();
                    if (message.equals("/quit")) {
                        inReader.close();
                        shutdown();
                    } else {
                        out.println(message);
                    }
                }

            } catch (Exception e) {
                shutdown();
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }
}
