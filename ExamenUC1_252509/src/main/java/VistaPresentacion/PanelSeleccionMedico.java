/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VistaPresentacion;

import Modelo.entidadesModelo.Medico;
import Modelo.entidadesModelo.Paciente;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

/**
 *
 * @author isaac
 */
public class PanelSeleccionMedico extends JPanel {

    private JLabel etiquetaBienvenida; // Etiqueta para dar la bienvenida al paciente
    private JPanel panelListaMedicos; // Panel interno que contendrá las tarjetas de cada medico PanelInfoMedico

    public PanelSeleccionMedico() {
        // Usamos BorderLayout para organizar el panel en secciones
        this.setLayout(new BorderLayout(10, 10));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margen alrededor

        //  Creamos y configuramos la etiqueta de bienvenida.
        etiquetaBienvenida = new JLabel("Bienvenido!", SwingConstants.CENTER);
        etiquetaBienvenida.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(etiquetaBienvenida, BorderLayout.NORTH); // La ponemos en la parte de arriba.

        // Configuramos el panel que contendrá la lista de tarjetas.
        panelListaMedicos = new JPanel();
        // Usamos BoxLayout para que las tarjetas se apilen una debajo de la otra.
        panelListaMedicos.setLayout(new BoxLayout(panelListaMedicos, BoxLayout.Y_AXIS));

        //  Creamos un JScrollPane y ponemos nuestro panel de lista dentro.
        // Esto es esencial para poder navegar si hay más médicos de los que caben en la pantalla.
        JScrollPane scrollPane = new JScrollPane(panelListaMedicos);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Médicos Disponibles"));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.add(scrollPane, BorderLayout.CENTER); // El scroll se posiciona en el centro.
    }

    // En PanelSeleccionMedico.java
    public void cargarInformacion(Paciente paciente, List<Medico> medicos) {
        
        etiquetaBienvenida.setText("¡Bienvenido, " + paciente.getNombreCompleto(TOOL_TIP_TEXT_KEY, TOOL_TIP_TEXT_KEY, TOOL_TIP_TEXT_KEY) + "!");
        panelListaMedicos.removeAll();

        
        // Iteramos sobre la lista de médicos que nos pasó el Controlador.
        for (Medico medico : medicos) {
            PanelInfoMedico tarjetaMedico = new PanelInfoMedico(medico);// Para cada medico, creamos una instancia de su tarjeta
            panelListaMedicos.add(tarjetaMedico);// Añadimos la tarjeta al panel de la lista
            panelListaMedicos.add(Box.createRigidArea(new Dimension(0, 10)));// Añadimos un pequeño espacio entre las tarjetas.
        }

        // Actualizamos la interfaz gráfica para que los cambios sean visibles.
        this.revalidate();
        this.repaint();
    }

    public JPanel getPanelListaMedicos() {
        return panelListaMedicos;
    }

    
}
