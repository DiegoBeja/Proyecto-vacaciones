import javax.swing.*;
import java.awt.*;

public class InterfazSeleccion extends JFrame {
    private JPanel panelPrincipal;
    private JPanel panelTitulo;
    private JLabel titulo;
    private JButton botonPiano;
    private JButton botonRegistro;
    private JButton botonSalir;

    public InterfazSeleccion(){
        setTitle("*Nombre de este pedo*");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0,0));

        //Panel principal
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridBagLayout());

        //Titulo
        titulo = new JLabel("Seleccione una opcion");
        titulo.setFont(new Font("Arial" , Font.BOLD,25));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelTitulo = new JPanel();
        panelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelTitulo.add(titulo);

        //Settings para acomodar los botones
        GridBagConstraints botones = new GridBagConstraints();
        botones.gridx = 0;
        botones.gridy = 0;
        botones.gridwidth = GridBagConstraints.REMAINDER;
        botones.fill = GridBagConstraints.HORIZONTAL;
        botones.weightx = 0;
        botones.insets = new Insets(10,10,10,10);

        //Aquí les pongo la imagen del boton y muevo los botones hacia abajo para acomodarlos
        panelPrincipal.add(botonPiano = new JButton("Piano"), botones);
        botonPiano.setIcon(new ImageIcon(new ImageIcon("src/Imagenes/botonChilo.png").getImage().getScaledInstance(125, 75, Image.SCALE_SMOOTH)));
        botonPiano.setPressedIcon(new ImageIcon(new ImageIcon("src/Imagenes/botonChilo2.png").getImage().getScaledInstance(125, 75, Image.SCALE_SMOOTH)));
        botonPiano.setHorizontalTextPosition(SwingConstants.CENTER);
        botonPiano.setFocusPainted(false);
        botonPiano.setContentAreaFilled(false);
        botonPiano.setBorderPainted(false);
        botones.gridy++;
        panelPrincipal.add(botonRegistro = new JButton("Registro"), botones);
        botonRegistro.setIcon(new ImageIcon(new ImageIcon("src/Imagenes/botonChilo.png").getImage().getScaledInstance(125, 75, Image.SCALE_SMOOTH)));
        botonRegistro.setPressedIcon(new ImageIcon(new ImageIcon("src/Imagenes/botonChilo2.png").getImage().getScaledInstance(125, 75, Image.SCALE_SMOOTH)));
        botonRegistro.setHorizontalTextPosition(SwingConstants.CENTER);
        botonRegistro.setFocusPainted(false);
        botonRegistro.setContentAreaFilled(false);
        botonRegistro.setBorderPainted(false);
        botones.gridy++;
        panelPrincipal.add(botonSalir = new JButton("Salir"), botones);
        botonSalir.setIcon(new ImageIcon(new ImageIcon("src/Imagenes/botonChilo.png").getImage().getScaledInstance(125, 75, Image.SCALE_SMOOTH)));
        botonSalir.setPressedIcon(new ImageIcon(new ImageIcon("src/Imagenes/botonChilo2.png").getImage().getScaledInstance(125, 75, Image.SCALE_SMOOTH)));
        botonSalir.setHorizontalTextPosition(SwingConstants.CENTER);
        botonSalir.setFocusPainted(false);
        botonSalir.setContentAreaFilled(false);
        botonSalir.setBorderPainted(false);

        //Agregar todo_al frame
        add(panelTitulo, BorderLayout.NORTH);
        add(panelPrincipal, BorderLayout.CENTER);
        setVisible(true);
    }
}
