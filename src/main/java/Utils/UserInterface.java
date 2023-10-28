package Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class UserInterface {
    private Icon imagenPpal;
    private static JFrame frame;
    private String[] opciones1;


    public void iniciarUi() throws IOException {
        imagenPpal = new ImageIcon(ImageIO.read(new File("Resources/Images/Hogwarts-logo.png")).getScaledInstance(150, 110, 2000));
        opciones1 = new String[]{"Gryffindor", "Slytherin", "Hufflepuff", "Ravenclaw"};
    }

    public String seleccionarOpcionesUi(){
        int opcionSeleccionada = JOptionPane.showInternalOptionDialog( frame, "Bienvenido a Hogwarts\nselecciona una casa:", "WikiHogwarts", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, imagenPpal, opciones1, null);
        return opciones1[opcionSeleccionada];
    }

    public void mostrarMensajeUi(String mensaje){
        JOptionPane.showMessageDialog(frame, mensaje, "WikiHogwarts", JOptionPane.INFORMATION_MESSAGE, imagenPpal);
    }

    public void cerrarUI(){
        JOptionPane.getRootFrame().dispose();
    }

}
