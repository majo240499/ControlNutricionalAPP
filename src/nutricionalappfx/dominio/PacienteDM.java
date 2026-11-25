/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nutricionalappfx.dominio;

import java.util.List;
import nutricionalappfx.dominio.contrato.IPacienteRepository;
import nutricionalappfx.dominio.pojo.Paciente;

/**
 *
 * @author Maria Jose
 */
public class PacienteDM {
    
    private IPacienteRepository contrato;
    
    public PacienteDM(IPacienteRepository contrato) {
        this.contrato = contrato;
    }
    
    public List<Paciente> obtenerPacientesNutriologo(int idNutriologo) throws RuntimeException {
        if (idNutriologo < 0) {
            throw new RuntimeException("Error al obtener la informacion.");
        }
        
        return contrato.obtenerPacienteNutriologo(idNutriologo);
    } 
}
