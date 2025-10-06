/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import Controlador.ControladorRegistro;
import Modelo.Modelo;
import javax.swing.SwingUtilities;

/**
 *
 * @author isaac
 */
public class main {

    public static void main(String[] args) {

        // SwingUtilities.invokeLater asegura que la creación de la interfaz
        // se realice en el hilo de despacho de eventos (Event Dispatch Thread),
        // que es la forma correcta y segura de iniciar aplicaciones Swing.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // 1. Crear el Modelo 
                Modelo modelo = new Modelo();

                // 2. Crear el Controlador
                ControladorRegistro controlador = new ControladorRegistro(modelo);

                // 3. Iniciar la aplicación
                controlador.iniciar();
            }
        });
    }

}
