import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class InterfazPiano extends JFrame {
    private JPanel historialCanciones;
    private JList<String> canciones;
    private JPanel notas;
    private Map<JButton, String> botonesNotas;
    private JPanel botonesGuardado;
    private JButton botonGuardar;
    private JButton botonListo;
    private JPanel visualizadorDeNotas;
    private JLabel tituloNotas;
    private JTextArea notasTocadas;
    private String[] datos = {"Rojo", "Verde", "Azul", "Blanco", "Morado"};
    private String[] notasNombre = {"C", "CS", "D", "DS", "E", "F", "FS", "G", "GS", "A", "AS", "B"};

    public InterfazPiano(){
        setTitle("Piano");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        visualizadorDeNotas = new JPanel();
        visualizadorDeNotas.setLayout(new BoxLayout(visualizadorDeNotas, BoxLayout.Y_AXIS));
        tituloNotas = new JLabel("Notas");
        tituloNotas.setAlignmentX(Component.CENTER_ALIGNMENT);
        visualizadorDeNotas.add(tituloNotas);
        notasTocadas = new JTextArea("Hola como estamos el dia de hoy");
        notasTocadas.setEditable(false);
        notasTocadas.setFocusable(false);
        notasTocadas.setAlignmentX(Component.CENTER_ALIGNMENT);
        visualizadorDeNotas.add(notasTocadas);
        visualizadorDeNotas.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        notas = new JPanel(new FlowLayout());
        botonesNotas = new HashMap<>();
        int contadorNotas = 1;
        for(int i=2; i<7; i++){
            for(int j=0; j<12; j++){
                if(contadorNotas <= 49) {
                    JButton notasBotones = new JButton(notasNombre[j] + i);
                    notasBotones.setFocusable(false);
                    botonesNotas.put(notasBotones, notasNombre[j] + i);
                    notas.add(notasBotones);
                    contadorNotas++;
                }
            }
        }

        botonesGuardado = new JPanel();
        botonGuardar = new JButton("Guardar");
        botonListo = new JButton("Listo");
        botonesGuardado.add(botonGuardar);
        botonesGuardado.add(botonListo);

        historialCanciones = new JPanel();
        historialCanciones.add(canciones = new JList<>(datos));
        historialCanciones.setBackground(new Color(100, 100, 100));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0.53;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(4, 4, 4, 4);

        add(visualizadorDeNotas, gbc);
        gbc.gridy++;
        add(notas, gbc);
        gbc.gridy++;
        add(botonesGuardado, gbc);

        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.weighty = 1;
        gbc.weightx = 0;
        add(historialCanciones, gbc);

        setVisible(true);
    }

}
