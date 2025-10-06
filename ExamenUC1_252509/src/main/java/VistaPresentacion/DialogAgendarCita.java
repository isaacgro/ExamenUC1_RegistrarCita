/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VistaPresentacion;

import Controlador.ControladorRegistro;
import Modelo.entidadesModelo.Medico;
import com.github.lgooddatepicker.components.DateTimePicker;
import java.awt.*;
import java.time.LocalDateTime;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import static javax.swing.JComponent.TOOL_TIP_TEXT_KEY;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author isaac
 */


public class DialogAgendarCita extends JDialog {
    private ControladorRegistro controlador;
    private Medico medico;
    
    // Componentes de la UI
    private DateTimePicker dateTimePicker;
    private JTextArea areaDescripcion;
    private JButton btnConfirmar;
    private JButton btnCancelar;

    public DialogAgendarCita(JFrame parent, boolean modal, Medico medico) {
        super(parent, modal);
        this.medico = medico;
        
        setTitle("Agendar Cita");
        setSize(450, 400);
        setLayout(new BorderLayout(10, 10));
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Panel de Información del Médico ---
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setBorder(BorderFactory.createTitledBorder("Datos de la Cita"));
        panelInfo.add(new JLabel("Médico: " + medico.getNombreCompleto(TOOL_TIP_TEXT_KEY, TOOL_TIP_TEXT_KEY, TOOL_TIP_TEXT_KEY)));
        panelInfo.add(new JLabel("Especialidad: " + medico.getEspecialidad().toString()));
        add(panelInfo, BorderLayout.NORTH);

        // --- Panel Central para Fecha y Descripción ---
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));

        panelCentral.add(new JLabel("Elige fecha y hora:"));
        dateTimePicker = new DateTimePicker();
        panelCentral.add(dateTimePicker);

        panelCentral.add(Box.createRigidArea(new Dimension(0, 15)));

        panelCentral.add(new JLabel("Descripción o motivo de la consulta:"));
        areaDescripcion = new JTextArea(5, 20);
        JScrollPane scrollDescripcion = new JScrollPane(areaDescripcion);
        panelCentral.add(scrollDescripcion);
        
        add(panelCentral, BorderLayout.CENTER);

        // --- Panel de Botones ---
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnConfirmar = new JButton("Confirmar Cita");
        btnCancelar = new JButton("Cancelar");
        panelBotones.add(btnCancelar);
        panelBotones.add(btnConfirmar);
        add(panelBotones, BorderLayout.SOUTH);

        // --- Acciones de los botones ---
        btnCancelar.addActionListener(e -> this.dispose());
        
        btnConfirmar.addActionListener(e -> confirmarCita());
        
        setLocationRelativeTo(parent);
    }
    
    public void setControlador(ControladorRegistro c) {
        this.controlador = c;
    }
    
    private void confirmarCita() {
        // Obtenemos la fecha y hora. El método "Strict" devuelve null si no se ha elegido algo.
        LocalDateTime fechaHoraSeleccionada = dateTimePicker.getDateTimeStrict();
        String descripcion = areaDescripcion.getText();

        if (fechaHoraSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona una fecha y una hora.", "Datos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Llamamos al controlador y, según el resultado, cerramos la ventana o no.
        boolean exito = controlador.registrarNuevaCita(this.medico, fechaHoraSeleccionada, descripcion);
        if (exito) {
            this.dispose(); // Cierra esta ventana si la cita se creó correctamente
        }
    }
}
