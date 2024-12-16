import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InterfazPiano extends JFrame {
    private JPanel historialCanciones;
    private JList<String> canciones;
    private JPanel notas;
    private Map<JButton, String> botonesNotas;
    private JPanel botonesGuardado;
    private JButton botonGuardar;
    private JButton botonTocar;
    private JPanel visualizadorDeNotas;
    private JLabel tituloNotas;
    private JTextArea notasTocadas;
    private DefaultListModel<String> cancionesModel;
    private String[] notasNombre = {"C", "CS", "D", "DS", "E", "F", "FS", "G", "GS", "A", "AS", "B"};

    public InterfazPiano(){
        setTitle("Piano");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        // Panel para donde se muestran las notas que se han tocado
        visualizadorDeNotas = new JPanel();
        visualizadorDeNotas.setLayout(new BoxLayout(visualizadorDeNotas, BoxLayout.Y_AXIS));
        tituloNotas = new JLabel("Notas");
        tituloNotas.setAlignmentX(Component.CENTER_ALIGNMENT);
        visualizadorDeNotas.add(tituloNotas);
        notasTocadas = new JTextArea();
        notasTocadas.setEditable(false);
        notasTocadas.setFocusable(false);
        notasTocadas.setAlignmentX(Component.CENTER_ALIGNMENT);
        visualizadorDeNotas.add(notasTocadas);
        visualizadorDeNotas.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Botones que contienen las notas
        notas = new JPanel(new FlowLayout());
        botonesNotas = new HashMap<>();
        int contadorNotas = 1;
        // Crear todos los botones de un piano de 49 teclas con sus respectivas notas
        for(int i=2; i<7; i++){
            for(int j=0; j<12; j++){
                String nombreBotonNota = notasNombre[j] + i;
                if(contadorNotas <= 49) {
                    JButton notasBotones = new JButton(nombreBotonNota);
                    notasBotones.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Si es la primera nota que se toca no se agrega "-"
                            if(notasTocadas.getText().equals("")){
                                notasTocadas.append(nombreBotonNota);
                            } else{
                                notasTocadas.append("-" + nombreBotonNota);
                            }
                        }
                    });

                    notasBotones.setFocusable(false);
                    botonesNotas.put(notasBotones, nombreBotonNota);
                    notas.add(notasBotones);
                    contadorNotas++;
                }
            }
        }

        // Botones de guardado
        botonesGuardado = new JPanel();
        botonGuardar = new JButton("Guardar");
        cancionesModel = new DefaultListModel<>();
        botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreArchivo = JOptionPane.showInputDialog("¿Qué nombre le quiere dar al archivo?");
                FileWriter cancion = null;
                try {
                    cancion = new FileWriter("C:\\Users\\bombo\\IdeaProjects\\Proyecto vacaciones\\src\\Canciones\\" + nombreArchivo + ".txt");
                    cancion.write(notasTocadas.getText());
                    cancion.close();
                    cancionesModel.addElement(nombreArchivo);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        botonTocar = new JButton("Tocar");
        botonesGuardado.add(botonGuardar);
        botonesGuardado.add(botonTocar);

        // Aquí se muestran todos los archivos de las canciones guardadas
        historialCanciones = new JPanel();
        canciones = new JList<>(cancionesModel);
        historialCanciones.add(canciones);
        historialCanciones.setBackground(new Color(100, 100, 100));

        // Esto es para acomodar todos los paneles
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