/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.entidadesModelo;

/**
 *
 * @author isaac
 */
public class Paciente {
    
    private String nombre;
     private String apellidoP;
    private String apellidoM;
    private int numSeguroSocial; // va ser el id
    private String correo;

    public Paciente(String nombre, String apellidoP, String apellidoM, int numSeguroSocial, String correo) {
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.numSeguroSocial = numSeguroSocial;
        this.correo = correo;
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

    public int getNumSeguroSocial() {
        return numSeguroSocial;
    }

    public void setNumSeguroSocial(int numSeguridadSocial) {
        this.numSeguroSocial = numSeguridadSocial;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public String getNombreCompleto(String nombre, String apellidoP, String apellidoM) {
        return this.nombre + " " + this.apellidoP + " " + this.apellidoM;
    }

    @Override
    public String toString() {
        return "Paciente{" + "nombre=" + nombre + ", apellidoP=" + apellidoP + ", apellidoM=" + apellidoM + ", numSeguridadSocial=" + numSeguroSocial + ", correo=" + correo + '}';
    }
    
    
}
