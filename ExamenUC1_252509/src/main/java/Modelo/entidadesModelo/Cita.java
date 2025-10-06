/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.entidadesModelo;

import java.time.LocalDateTime;

/**
 *
 * @author isaac
 */
public class Cita {
    
    private int idCita;
    private Paciente paciente;
    private Medico medico;
    private LocalDateTime fechaHora;
    private String descripcion;
    private boolean estado; // confirmada o cancelada

    public Cita(int idCita, Paciente paciente, Medico medico, LocalDateTime fechaHora, String descripcion, boolean estado) {
        this.idCita = idCita;
        this.paciente = paciente;
        this.medico = medico;
        this.fechaHora = fechaHora;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    

  
    
    

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cita{" + "idCita=" + idCita + ", descripcion=" + descripcion + ", paciente=" + paciente + ", medico=" + medico + ", fechaHora=" + fechaHora + ", estado=" + estado + '}';
    }

   
    
    
    
}