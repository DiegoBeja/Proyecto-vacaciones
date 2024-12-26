import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.sound.sampled.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
    private JButton botonBorrar;
    private JPanel visualizadorDeNotas;
    private JLabel tituloNotas;
    private JTextArea notasTocadas;
    private JTextField botonMs;
    private int ms;
    private DefaultListModel<String> cancionesModel;
    private String[] notasNombre = {"C", "CS", "D", "DS", "E", "F", "FS", "G", "GS", "A", "AS", "B"};
    double[] frecuencias = {
            65.41, 69.30, 73.42, 77.78, 82.41, 87.31, 92.50, 98.00, 103.83, 110.00, 116.54, 123.47,
            130.81, 138.59, 146.83, 155.56, 164.81, 174.61, 185.00, 196.00, 207.65, 220.00, 233.08, 246.94,
            261.63, 277.18, 293.66, 311.13, 329.63, 349.23, 369.99, 392.00, 415.30, 440.00, 466.16, 493.88,
            523.25, 554.37, 587.33, 622.25, 659.25, 698.46, 739.99, 783.99, 830.61, 880.00, 932.33, 987.77,
            1046.50
    };

    public InterfazPiano() {
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
        for (int i = 2; i < 7; i++) {
            for (int j = 0; j < 12; j++) {
                String nombreBotonNota = notasNombre[j] + i;
                if (contadorNotas <= 49) {
                    JButton notasBotones = new JButton(nombreBotonNota);
                    double frecuencia = frecuencias[i];
                    notasBotones.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Si es la primera nota que se toca no se agrega "-"
                            if (notasTocadas.getText().equals("")) {
                                notasTocadas.append(nombreBotonNota);
                            } else {
                                notasTocadas.append("-" + nombreBotonNota);
                            }
                            reproducirNota(frecuencia, ms);

                            //Al momento de que se presiona una nota, es valido el botón borrar ya que hay texto en el TextArea
                            botonBorrar.setEnabled(true);
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
                if(notasTocadas.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"No hay ninguna nota tocada", "Error" ,JOptionPane.ERROR_MESSAGE);
                } else {
                    String nombreArchivo = JOptionPane.showInputDialog("¿Qué nombre le quiere dar al archivo?");
                    try (
                            FileWriter TodasLasCanciones = new FileWriter("C:\\Users\\bombo\\IdeaProjects\\Proyecto vacaciones\\src\\Canciones\\TodasLasCanciones.txt", true);
                            FileWriter cancion = new FileWriter("C:\\Users\\bombo\\IdeaProjects\\Proyecto vacaciones\\src\\Canciones\\" + nombreArchivo + ".txt")
                    ) {
                        TodasLasCanciones.write(nombreArchivo + "\n");
                        cancion.write(notasTocadas.getText());
                        notasTocadas.setText("");

                        cancionesModel.addElement(nombreArchivo);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        //Botón para borrar notas puestas en el TextArea
        botonBorrar = new JButton("Borrar");
        botonBorrar.setEnabled(false);
        botonBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Se verifica si no está vacio para poder comenzar a "vaciar" la ultima nota
                if (!notasTocadas.getText().isEmpty()) {

                    //Se separa las notas quitando el "-"
                    String[] textoSacadoParaBorrar = notasTocadas.getText().split("-");

                    //Sí hay tengo en el TextArea, se crea un StringBuilder para crear el nuevo String sin la ultima nota
                    if (textoSacadoParaBorrar.length != 0) {
                        StringBuilder textoNuevoSacadoParaBorrar = new StringBuilder();

                        //Por cada elemento de la cadena separada por "-" se alamcenará en el StringBuilder pero no el ultimo
                        for (int i = 0; i < textoSacadoParaBorrar.length - 1; ++i) {

                            //Si es el penultimo elemento, no se agrega el guión
                            if (i == textoSacadoParaBorrar.length - 2) {
                                textoNuevoSacadoParaBorrar.append(textoSacadoParaBorrar[i]);

                                //Si no es el penultimo elemento, entonces si se agrega el guión
                            } else {
                                textoNuevoSacadoParaBorrar.append(textoSacadoParaBorrar[i]).append("-");
                            }

                        }

                        //Ya por ultimo se agrega el StringBuilder otra vez al TextArea pero sin el ultimo elemento ya que se pretende eliminar
                        //una vez seleccionado el botón borrar
                        notasTocadas.setText(textoNuevoSacadoParaBorrar.toString());
                    }
                }
            }
        });

        botonTocar = new JButton("Tocar");
        botonTocar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] notas = notasTocadas.getText().split("-");
                int posicion = 0;
                for (Map.Entry<JButton, String> entrada : botonesNotas.entrySet()) {
                    String nota = entrada.getValue();

                    for (String notaTocada : notas) {
                        if (notaTocada.equals(nota)) {
                            double frecuancia = frecuencias[posicion];
                            System.out.println("Nota coincidente: " + nota + " en posición: " + posicion);
                            reproducirNota(frecuancia, ms);
                        }
                    }
                    posicion++;
                }
            }
        });

        ms = 500;
        botonMs = new JTextField();
        botonMs.setPreferredSize(new Dimension(100, 27));
        botonMs.setText(String.valueOf(ms));
        botonMs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ms = Integer.parseInt(botonMs.getText());
            }
        });

        botonesGuardado.add(botonGuardar);
        botonesGuardado.add(botonTocar);
        botonesGuardado.add(botonBorrar);
        botonesGuardado.add(botonMs);

        // Aquí se muestran todos los archivos de las canciones guardadas
        historialCanciones = new JPanel();
        try(BufferedReader leer = new BufferedReader(new FileReader("C:\\Users\\bombo\\IdeaProjects\\Proyecto vacaciones\\src\\Canciones\\TodasLasCanciones.txt"));) {
            String linea;
            while ((linea = leer.readLine()) != null) {
                cancionesModel.addElement(linea);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        canciones = new JList<>(cancionesModel);

        // Cuando se selecciona un archivo del historial se muestran las notas que contiene
        canciones.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int estadoSeleccionArchivo = JOptionPane.showConfirmDialog(null, "¿Desea cargar esta melodía?", "Selección" ,JOptionPane.YES_NO_OPTION);
                if(estadoSeleccionArchivo == 0){
                    try(BufferedReader leer = new BufferedReader(new FileReader("C:\\Users\\bombo\\IdeaProjects\\Proyecto vacaciones\\src\\Canciones\\" + canciones.getSelectedValue() + ".txt"))) {
                        String linea;
                        // Se hace reset a las notas mostradas
                        notasTocadas.setText("");
                        while((linea = leer.readLine()) != null){
                            notasTocadas.append(linea);
                        }
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } ;
                }

            }
        });
        canciones.setBackground(new Color(100, 100, 100));
        canciones.setForeground(new Color(255, 255, 255));
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

    private void reproducirNota(double frecuencia, int duracion)
    {
        float frecuenciaMuestreo = 44100;
        byte[] buffer = new byte[(int) frecuenciaMuestreo * duracion / 1000];

        for (int i = 0; i < buffer.length; i++) {
            double angulo = 2.0 * Math.PI * i / (frecuenciaMuestreo / frecuencia);
            buffer[i] = (byte) (Math.sin(angulo) * 50); // Onda sinusoidal
        }

        AudioFormat formato = new AudioFormat(frecuenciaMuestreo, 8, 1, true, false);
        try {
            SourceDataLine linea = AudioSystem.getSourceDataLine(formato);
            linea.open(formato);
            linea.start();
            linea.write(buffer,0,buffer.length);
            linea.drain();
            linea.close();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
}