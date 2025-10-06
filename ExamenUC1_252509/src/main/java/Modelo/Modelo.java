/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import ModeloDaos.PacienteDAO;
import ModeloDaos.CitaDAO;
import ModeloDaos.MedicoDAO;
import Modelo.entidadesModelo.Cita;
import Modelo.entidadesModelo.Medico;
import Modelo.entidadesModelo.Paciente;
import Observer.Observer;
import Observer.Subject;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author isaac
 */
public class Modelo implements Subject {

    private PacienteDAO pacienteDAO;
    private MedicoDAO medicoDAO;
    private CitaDAO citaDAO;

    private Paciente pacienteActual;
    private List<Medico> listaMedicos;
    private Cita citaGenerada;
    private List<Cita> listaCitas;

    // lista de observers
    private List<Observer> observers;

    public Modelo() {
        this.pacienteDAO = new PacienteDAO();
        this.medicoDAO = new MedicoDAO();
        this.citaDAO = new CitaDAO();
        this.observers = new ArrayList<>();

        // precargamos medicos 
        this.listaMedicos = medicoDAO.obtenerTodosMedicos();
    }

    public void buscarPaciente(int nss) {
        this.pacienteActual = pacienteDAO.buscarPacientePorNss(nss);
        
        
        if (this.pacienteActual != null) {
            this.listaCitas = citaDAO.buscarCitasPorPaciente(this.pacienteActual);
        } else {
            this.listaCitas = new ArrayList<>(); // Si no, la lista está vacía
        }

        notifyObservers();
    }

    public void crearNuevaCita(Medico medico, LocalDateTime fechaHora, String descripcion) {

        if (this.pacienteActual != null) {
            citaDAO.registrarCita(this.pacienteActual, medico, fechaHora, descripcion);

            this.listaCitas = citaDAO.buscarCitasPorPaciente(this.pacienteActual);
            notifyObservers();
        }
    }

    // es horario valido
    public boolean esHorarioValido(Medico medico, LocalDateTime fechaHoraCita) {
        // Obtenemos el día de la semana de la cita propuesta 
        DayOfWeek diaDeLaCita = fechaHoraCita.getDayOfWeek();

        //  Verificamos si el médico trabaja ese día
        if (!medico.getDiasLaborales().contains(diaDeLaCita)) {
            return false; // No trabaja ese día
        }

        // Obtenemos la hora de la cita propuesta (ej. 10:30)
        LocalTime horaDeLaCita = fechaHoraCita.toLocalTime();

        //Verificamos si la hora está dentro del rango laboral
        // (es después de la hora de inicio Y antes de la hora de fin)
        if (horaDeLaCita.isBefore(medico.getHoraInicio()) || horaDeLaCita.isAfter(medico.getHoraFin())) {
            return false; // Está fuera del horario
        }

        // Si pasó todas las validaciones, el horario es válido
        return true;
    }

    // GETTERS PARA QUE LA VISTA OBTENGA LOS DATOS 
    public Paciente getPacienteActual() {
        return pacienteActual;
    }

    public List<Medico> getMedicosDisponibles() {
        return listaMedicos;
    }

    public Cita getCitaGenerada() {
        return citaGenerada;
    }
    
    public List<Cita> getCitasDelPaciente() {
        return this.listaCitas;
    }

    public List<Cita> obtenerTodasCita() {
        return listaCitas;
    }
    
    public Medico getMedicoPorId(int id ) {
        return medicoDAO.buscarMedicoPorId(id);
    }

    // --- MÉTODOS DEL PATRÓN OBSERVER ---
    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

}
