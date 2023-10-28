package Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class UserInterface {

    //Declare class variables
    private Icon imagenPpal;
    private static JFrame frame;
    private String[] opciones1;

    /*
    Init User Interface config
     */
    public void iniciarUi() throws IOException {
        imagenPpal = new ImageIcon(ImageIO.read(new File("Resources/Images/Hogwarts-logo.png")).getScaledInstance(150, 110, 2000));
        opciones1 = new String[]{"Gryffindor", "Slytherin", "Hufflepuff", "Ravenclaw"};
    }

    /*
    Show options and let the user select one
     */
    public String seleccionarOpcionesUi(){
        int opcionSeleccionada = JOptionPane.showInternalOptionDialog( frame, "Bienvenido a Hogwarts\nselecciona una casa:", "WikiHogwarts", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, imagenPpal, opciones1, null);
        return opciones1[opcionSeleccionada];
    }

    /*
    Show message into user interface
     */
    public void mostrarMensajeUi(String mensaje){
        JOptionPane.showMessageDialog(frame, mensaje, "WikiHogwarts", JOptionPane.INFORMATION_MESSAGE, imagenPpal);
    }

    /*
    Close user interface
     */
    public void cerrarUI(){
        JOptionPane.getRootFrame().dispose();
    }

}
