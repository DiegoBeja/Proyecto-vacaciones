import javax.swing.*;
import java.awt.*;

public class InterfazBiblioteca extends JFrame {

    public InterfazBiblioteca(){
        setTitle("Biblioteca");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());


        setVisible(true);
    }
}
