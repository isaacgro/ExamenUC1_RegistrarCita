/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import ModeloDaos.CitaDAO;
import ModeloDaos.MedicoDAO;
import Modelo.Modelo;
import Modelo.entidadesModelo.Cita;
import ModeloDaos.PacienteDAO;
import Modelo.entidadesModelo.Medico;
import Modelo.entidadesModelo.Paciente;
import VistaPresentacion.DialogAgendarCita;
import VistaPresentacion.DialogLogin;
import VistaPresentacion.MenuPaciente;
import VistaPresentacion.MenuPrincipal;
import VistaPresentacion.PanelInfoMedico;
import VistaPresentacion.PanelSeleccionMedico;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.Box;
import javax.swing.JButton;
import static javax.swing.JComponent.TOOL_TIP_TEXT_KEY;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author isaac
 */
public class ControladorRegistro {

    private Modelo modelo;
    private JFrame menuPrincipal;
    private JFrame menuPaciente; // referencia a 

    public ControladorRegistro(Modelo modelo) {
        this.modelo = modelo;

    }

    //------------------------------- METODOS DE NAVEGACION ---------------------------------------------------------------------------------------
    public void iniciar() {
        MenuPrincipal vistaPrincipal = new MenuPrincipal();
        vistaPrincipal.setControlador(this);
        vistaPrincipal.setVisible(true);

    }

    public void mostrarDialogLogin(JFrame padre) {
        DialogLogin dialogLogin = new DialogLogin(padre, true);
        dialogLogin.setControlador(this);
        dialogLogin.setVisible(true);
    }
    
    

    public void validarPacienteYMostrarMenu(int nss, DialogLogin dialogo) {
        modelo.buscarPaciente(nss);
        Paciente paciente = modelo.getPacienteActual();

        if (paciente != null) {
            dialogo.dispose(); // Cierra el diálogo de login

            // AQUI ABRIMOS LA NUEVA PANTALLA DEL MENU PACIENTE
            mostrarMenuPaciente();

        } else {
            JOptionPane.showMessageDialog(dialogo, "Paciente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mostrarMenuPaciente() {
        if (this.menuPaciente != null) {
            this.menuPaciente.dispose();
        }
        // Creamos la nueva ventana pasándole el modelo para que pueda "observarlo".
        MenuPaciente vistaPaciente = new MenuPaciente(modelo);
        vistaPaciente.setControlador(this);

        // Llamamos al metodo de conectarFuncionBotones para que ponga los botones funcionando
        conectarBotonesDeMedico(vistaPaciente);

        this.menuPaciente = vistaPaciente;
        this.menuPaciente.setVisible(true);
    }
    
    //------------------------------- METODOS DE CITA ---------------------------------------------------------------------------------------

    
    // recorre cada tarjeta de medico y asigna un boton
    private void conectarBotonesDeMedico(MenuPaciente vista) {
        PanelSeleccionMedico panel = vista.getPanelSeleccionMedico();
        for (Component comp : panel.getPanelListaMedicos().getComponents()) {
            
            if (comp instanceof PanelInfoMedico) {
                PanelInfoMedico tarjeta = (PanelInfoMedico) comp;
                JButton boton = tarjeta.getBtnAgendarCita();
                
                int idDelMedico = tarjeta.getIdMedico(); // agarramos el id del medico

                // Limpiamos listeners viejos para evitar que se acumulen.
                for (ActionListener al : boton.getActionListeners()) {
                    boton.removeActionListener(al);
                }

                // Creamos el ActionListener.
                boton.addActionListener(e -> {
                    // Cuando se haga clic, le pedimos al Modelo el objeto Medico completo.
                    Medico medicoSeleccionado = modelo.getMedicoPorId(idDelMedico);

                    //  Si lo encontramos, continuamos con el flujo normal.
                    if (medicoSeleccionado != null) {
                        iniciarDialogDeCita(medicoSeleccionado);
                    }
                });
            }
        }
    }

    public void iniciarDialogDeCita(Medico medico) {
        DialogAgendarCita dialogo = new DialogAgendarCita(this.menuPaciente, true, medico);
        dialogo.setControlador(this);
        dialogo.setVisible(true);
    }

    

    public boolean registrarNuevaCita(Medico medico, LocalDateTime fechaHora, String descripcion) {
        // llamamos el metodo del modelo para validar
        if (!modelo.esHorarioValido(medico, fechaHora)) {
            JOptionPane.showMessageDialog(this.menuPaciente,
                    "La fecha seleccionada está fuera del horario laboral del médico.",
                    "Horario no válido",
                    JOptionPane.ERROR_MESSAGE);
            return false; // La cita no es válida
        }

        // Si todo es válido, le pedimos al modelo que cree la cita
        modelo.crearNuevaCita(medico, fechaHora, descripcion);

        JOptionPane.showMessageDialog(this.menuPaciente,
                "Cita confirmada con éxito.",
                "Cita Agendada",
                JOptionPane.INFORMATION_MESSAGE);

        return true; // La cita se creó con éxito
    }

    // metodo para mostrar citas al paciente
    public void buscarPacienteYMostrarCitas(int nss, JDialog dialogo) {
        modelo.buscarPaciente(nss); // Este método ya carga las citas del paciente
        Paciente paciente = modelo.getPacienteActual();

        if (paciente != null) {
            dialogo.dispose(); // Cierra el diálogo de login

            List<Cita> citas = modelo.getCitasDelPaciente();
            if (citas == null || citas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No tienes ninguna cita agendada.");
                return;
            }

            // Formateamos y mostramos las citas en un JOptionPane
            StringBuilder sb = new StringBuilder("<html>Tus Citas:<br><br>");
            for (Cita cita : citas) {
                sb.append("<b>Médico:</b> ").append(cita.getMedico().getNombreCompleto(TOOL_TIP_TEXT_KEY, TOOL_TIP_TEXT_KEY, TOOL_TIP_TEXT_KEY)).append("<br>");
                sb.append("<b>Fecha:</b> ").append(cita.getFechaHora().toLocalDate()).append("<br>");
                sb.append("<b>Hora:</b> ").append(cita.getFechaHora().toLocalTime()).append("<br>");
                sb.append("<b>Motivo:</b> ").append(cita.getDescripcion()).append("<hr>");
            }
            sb.append("</html>");

            JOptionPane.showMessageDialog(null, sb.toString(), "Mis Citas", JOptionPane.INFORMATION_MESSAGE);

        } else {
            JOptionPane.showMessageDialog(dialogo, "Paciente no encontrado.");
        }
        iniciar();
    }
    

}
