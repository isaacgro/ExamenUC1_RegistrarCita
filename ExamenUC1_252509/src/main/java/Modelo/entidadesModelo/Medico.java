/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.entidadesModelo;

import Modelo.utilerias.especialidad;
import Modelo.utilerias.especialidad;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

/**
 *
 * @author isaac
 */
public class Medico {

    private int idMedico;
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private especialidad especialidad;
    private String consultorio;

    // dias consulta
    private Set<DayOfWeek> diasLaborales; // dias de la semana lunes
    private LocalTime horaInicio;
    private LocalTime horaFin;

    public Medico(int idMedico, String nombre, String apellidoP, String apellidoM, especialidad especialidad, String consultorio, Set<DayOfWeek> diasLaborales, LocalTime horaInicio, LocalTime horaFin) {
        this.idMedico = idMedico;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.especialidad = especialidad;
        this.consultorio = consultorio;
        this.diasLaborales = diasLaborales;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        
    }

    public Set<DayOfWeek> getDiasLaborales() {
        return diasLaborales;
    }

    public void setDiasLaborales(Set<DayOfWeek> diasLaborales) {
        this.diasLaborales = diasLaborales;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public String getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(String consultorio) {
        this.consultorio = consultorio;
    }

    public String getNombreCompleto(String nombre, String apellidoP, String apellidoM) {
        return this.nombre + " " + this.apellidoP + " " + this.apellidoM;
    }

}
