/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDaos;

import Modelo.entidadesModelo.Paciente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author isaac
 */
public class PacienteDAO {

    private List<Paciente> listaPacientes;

    public PacienteDAO() {
        this.listaPacientes = new ArrayList<>();
         listaPacientes.add(new Paciente("Fernando", "Fernandez", "Hernandez", 0, "isaac@gmail.com"));
        listaPacientes.add(new Paciente("Isaac", "Guerrero", "Ayon", 1, "isaac@gmail.com"));

    }

    public void guardarPaciente(Paciente nuevoPaciente) {
        this.listaPacientes.add(nuevoPaciente);
    }

    public List obtenerTodosPacientes() {
        return this.listaPacientes;
    }

    public Paciente buscarPacientePorNss(int Nss) {
        for (Paciente p : listaPacientes) {
            if (p.getNumSeguroSocial() == Nss) {
                return p;
            }

        }
         System.out.println("No existe ese paciente con ese nss");
        return null;
    }
}
