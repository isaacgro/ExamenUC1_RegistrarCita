/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDaos;

import Modelo.entidadesModelo.Medico;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static Modelo.utilerias.especialidad.Cardiologia;
import static Modelo.utilerias.especialidad.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.EnumSet;

/**
 *
 * @author isaac
 */
public class MedicoDAO {

    private List<Medico> listaMedicos;

    public MedicoDAO() {
        this.listaMedicos = new ArrayList<>();
        LocalDateTime fecha = LocalDateTime.now();
        listaMedicos.add(new Medico(0, "Gregorio", "Casa", "Ramirez", Cardiologia,  "1824",EnumSet.of(DayOfWeek.MONDAY),LocalTime.of(9, 0), LocalTime.of(13, 0)));
        listaMedicos.add(new Medico(1, "Lauro", "Cuevas", "Rodriguez", Neurologia,  "1824", EnumSet.of(DayOfWeek.FRIDAY),LocalTime.of(9, 0), LocalTime.of(14, 0)));
    }

    public List<Medico> obtenerTodosMedicos() {

        return this.listaMedicos;
    }
    
    public Medico buscarMedicoPorId(int id) {
        for(Medico medico: listaMedicos) {
            if (medico.getIdMedico() == id) {
                return medico;
            }
        } 
        return null;
        
    }

}
