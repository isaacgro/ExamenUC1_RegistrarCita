/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VistaPresentacion;

import Modelo.entidadesModelo.Medico;
import javax.swing.JPanel;

/**
 *
 * @author isaac
 */

import javax.swing.*;
import java.awt.*;

import javax.swing.*;
import java.awt.*;
import static javax.swing.JComponent.TOOL_TIP_TEXT_KEY;


public class PanelInfoMedico extends JPanel {

    // Atributos de la clase
    private final int idMedico; // Solo se guarda el ID, es final porque no cambiará.
    private final JButton btnAgendarCita;

   
    public PanelInfoMedico(Medico medico) {
        //  Guardamos el ID del médico. Esta es la única información que la tarjeta recordará.
        this.idMedico = medico.getIdMedico();

        //  Creamos los componentes de la UI
        JLabel nombreLabel = new JLabel(medico.getNombreCompleto(TOOL_TIP_TEXT_KEY, TOOL_TIP_TEXT_KEY, TOOL_TIP_TEXT_KEY));
        nombreLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel especialidadLabel = new JLabel("Especialidad: " + medico.getEspecialidad().toString());
        JLabel consultorioLabel = new JLabel("Consultorio: " + medico.getConsultorio());
        
        // Formateamos el horario para que sea legible
        String horario = "Horario: " + medico.getDiasLaborales().toString() 
                       + " de " + medico.getHoraInicio() + " a " + medico.getHoraFin();
        JLabel diasLabel = new JLabel(horario);

        this.btnAgendarCita = new JButton("Agendar Cita");
        
        // 3. Organizamos el layout del panel
        setBorder(BorderFactory.createTitledBorder(medico.getNombreCompleto(TOOL_TIP_TEXT_KEY, TOOL_TIP_TEXT_KEY, TOOL_TIP_TEXT_KEY)));
        setLayout(new BorderLayout(20, 0));

        JPanel panelDetalles = new JPanel();
        panelDetalles.setLayout(new BoxLayout(panelDetalles, BoxLayout.Y_AXIS));
        panelDetalles.add(nombreLabel);
        panelDetalles.add(Box.createRigidArea(new Dimension(0, 5)));
        panelDetalles.add(especialidadLabel);
        panelDetalles.add(consultorioLabel);
        panelDetalles.add(diasLabel);
        
        JPanel panelBoton = new JPanel(new GridBagLayout());
        panelBoton.add(btnAgendarCita);
        
        add(panelDetalles, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.EAST);
    }


    public int getIdMedico() {
        return idMedico;
    }

   
    public JButton getBtnAgendarCita() {
        return btnAgendarCita;
    }
}
