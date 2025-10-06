/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Modelo.utilerias;

/**
 *
 * @author isaac
 */
public enum especialidad {
    // la descripcion para usarla en la presentacion y que se vea bonito
    Cardiologia("Cardiologia"),
    Neurologia("Neurologia"),
    Odontologia("Odontologia"),
    Traumatologia("Traumatologia"),
    Anestesiologia("Anestesiologia");
    
 

    private final String descripcion;

    especialidad(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}

    
    

