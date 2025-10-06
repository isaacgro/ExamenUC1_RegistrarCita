/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDaos;

import Modelo.entidadesModelo.Cita;
import Modelo.entidadesModelo.Paciente;
import Modelo.entidadesModelo.Medico;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author isaac
 */
public class CitaDAO {

    private List<Cita> listaDeCitas;
    private int idCita = 0;

    public CitaDAO() {
        this.listaDeCitas = new ArrayList<>();

    }

    public Cita registrarCita(Paciente paciente, Medico medico, LocalDateTime fechaHora, String descripcion) {

        // 1. Preparamos los datos que el sistema genera: el ID y el estado inicial.
        int nuevoId = this.idCita;
        boolean estadoInicial = true; // true = confirmada

        // 2. Usamos el ÚNICO constructor de Cita para crear el objeto.
        //    Ahora sí estamos usando el parámetro 'descripcion' que recibimos.
        Cita nuevaCita = new Cita(nuevoId, paciente, medico, fechaHora, descripcion, estadoInicial);

        // 3. Guardamos la nueva cita en nuestra "base de datos".
        this.listaDeCitas.add(nuevaCita);

        // 4. Preparamos el ID para la siguiente cita que se cree.
        this.idCita++;

        // 5. Devolvemos la cita recién creada.
        return nuevaCita;
    }

    public List<Cita> obtenerTodasCitas() {
        return this.listaDeCitas;
    }
    
    public List<Cita> buscarCitasPorPaciente(Paciente paciente) {
        return listaDeCitas.stream()
                .filter(cita -> cita.getPaciente().getNumSeguroSocial() == paciente.getNumSeguroSocial())
                .collect(Collectors.toList());
    }


}
