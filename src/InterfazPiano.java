import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class InterfazPiano extends JFrame {
    private JPanel historialCanciones;
    private JPanel notas;
    private JPanel botonesGuardado;
    private JButton botonGuardar;
    private JButton botonListo;
    private Map<JButton, String> botonesNotas;
    private JList<String> canciones;
    private String[] datos = {"Rojo", "Verde", "Azul", "Blanco", "Morado"};

    public InterfazPiano(){
        setTitle("Piano");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        botonesGuardado = new JPanel();
        botonGuardar = new JButton("Guardar");
        botonListo = new JButton("Listo");
        botonesGuardado.add(botonGuardar);
        botonesGuardado.add(botonListo);

        notas = new JPanel();


        historialCanciones = new JPanel();
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.gridwidth = GridBagConstraints.REMAINDER;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.weightx = 0;
//        gbc.insets = new Insets(4, 4, 4, 4);

        historialCanciones.add(canciones = new JList<>(datos));
        historialCanciones.setBackground(new Color(100, 100, 100));
        add(historialCanciones);
        add(botonesGuardado);

        setVisible(true);
    }

}
