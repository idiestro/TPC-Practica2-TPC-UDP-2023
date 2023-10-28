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

    public String seleccionarOpcionesUi() throws Exception{
        int opcionSeleccionada = JOptionPane.showInternalOptionDialog(frame, "Bienvenido a Hogwarts\nselecciona una casa:", "WikiHogwarts", 0, 3, imagenPpal, opciones1, (Object)null);
        return opciones1[opcionSeleccionada];
    }

    public void mostrarMensajeUi(String mensaje) throws Exception{
        JOptionPane.showMessageDialog(frame, mensaje, "WikiHogwarts", 1, imagenPpal);
    }

    public void cerrarUI() throws Exception{
        JOptionPane.getRootFrame().dispose();
    }

}
