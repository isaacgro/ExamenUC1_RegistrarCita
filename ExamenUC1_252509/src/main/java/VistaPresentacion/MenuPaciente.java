/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VistaPresentacion;

import Controlador.ControladorRegistro;
import Modelo.Modelo;
import Modelo.entidadesModelo.Medico;
import Modelo.entidadesModelo.Paciente;
import Observer.Observer;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author isaac
 */
public class MenuPaciente extends JFrame implements Observer {

    private PanelSeleccionMedico panelSeleccionMedico;
    private ControladorRegistro controlador;
    private Modelo modelo;

    
    public MenuPaciente(Modelo modelo) {
        this.modelo = modelo;
        this.modelo.addObserver(this);
        setTitle("Portal del Paciente - Seleccionar Médico");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Corregido
        setLayout(new BorderLayout());

        // El panel de selección se crea vacío
        panelSeleccionMedico = new PanelSeleccionMedico();

        actualizarVista();

        add(panelSeleccionMedico, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnSalir = new JButton("Salir");
        panelInferior.add(btnSalir);
        add(panelInferior, BorderLayout.SOUTH);

        btnSalir.addActionListener(e -> {
            this.dispose();
            if (controlador != null) {
                controlador.iniciar(); // Vuelve al menú principal
            }
        });

        setSize(700, 600);
        setLocationRelativeTo(null);
    }

    public void setControlador(ControladorRegistro controlador) {
        this.controlador = controlador;
    }

    public PanelSeleccionMedico getPanelSeleccionMedico() {
        return panelSeleccionMedico;
    }

    public void mostrarInformacion(Paciente paciente, List<Medico> medicos) {
        this.panelSeleccionMedico.cargarInformacion(paciente, medicos);
    }

    @Override
    public void update() {
        System.out.println("MenuPaciente recibió una notificación del Modelo y se actualizará.");
        actualizarVista();
    }

    
    private void actualizarVista() {
        Paciente paciente = modelo.getPacienteActual();
        List<Medico> medicos = modelo.getMedicosDisponibles();
        this.panelSeleccionMedico.cargarInformacion(paciente, medicos);
    }

}
